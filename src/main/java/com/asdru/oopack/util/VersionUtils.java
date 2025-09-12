package com.asdru.oopack.util;

import com.asdru.oopack.internal.VersionInfo;
import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class VersionUtils {

    private static final Logger LOGGER = Logger.getLogger(VersionUtils.class.getName());
    private static final Path resourcesPath = Path.of("src", "main", "resources",
            "_generated", "versions.json");
    private static final String VERSIONS_URL =
            "https://raw.githubusercontent.com/misode/mcmeta/refs/heads/summary/versions/data.json";

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static void generateVersionFile() {
        try {
            Files.createDirectories(resourcesPath.getParent());

            Optional<Map<String, VersionInfo>> optionalVersions = VersionUtils.getVersionOptional();

            if (optionalVersions.isEmpty()) {
                LOGGER.severe("Failed to fetch version data, file not created.");
                return;
            }

            Map<String, VersionInfo> versions = optionalVersions.get();

            JsonObject root = new JsonObject();
            root.addProperty("created_at", DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
            root.add("versions", GSON.toJsonTree(versions));

            try (FileWriter writer = new FileWriter(resourcesPath.toFile(), StandardCharsets.UTF_8)) {
                GSON.toJson(root, writer);
            }
            LOGGER.info("Version file generated: " + resourcesPath.toAbsolutePath());
        } catch (IOException e) {
            LOGGER.severe("Error writing version file: " + e.getMessage());
        }
    }

    private static boolean needsUpdate() {
        Optional<JsonObject> jsonOpt = loadExistingJson();

        if (jsonOpt.isEmpty()) {
            return true; // file missing, need to create it
        }

        JsonObject existingRoot = jsonOpt.get();
        if (existingRoot.has("created_at")) {
            try {
                Instant createdAt = Instant.parse(existingRoot.get("created_at").getAsString());
                long ageMillis = System.currentTimeMillis() - createdAt.toEpochMilli();

                if (ageMillis < 24 * 60 * 60 * 1000L) {
                    LOGGER.info("Version file is less than one day old, skipping fetch.");
                    return false;
                }
                return true;
            } catch (Exception e) {
                LOGGER.warning("Invalid created_at format, forcing update. Reason: " + e.getMessage());
                return true;
            }
        }

        return true; // if no created_at field, force update
    }

    private static Optional<JsonObject> loadExistingJson() {
        if (!Files.exists(resourcesPath)) {
            return Optional.empty();
        }

        try (var reader = Files.newBufferedReader(resourcesPath, StandardCharsets.UTF_8)) {
            JsonObject existingRoot = JsonParser.parseReader(reader).getAsJsonObject();
            return Optional.of(existingRoot);
        } catch (Exception e) {
            LOGGER.warning("Could not parse existing version file: " + e.getMessage());
            return Optional.empty();
        }
    }

    public static VersionInfo getVersionInfo(String versionKey) {
        try {
            // if its missing or needs update, create file
            if (!Files.exists(resourcesPath) || needsUpdate()) {
                LOGGER.info("Version file missing or outdated, regenerating...");
                generateVersionFile();
            }

            Optional<JsonObject> existingRootOpt = loadExistingJson();
            if (existingRootOpt.isEmpty()) {
                throw new IllegalStateException("No version file found at " + resourcesPath);
            }

            JsonObject existingRoot = existingRootOpt.get();
            if (!existingRoot.has("versions")) {
                throw new IllegalStateException("Version file is missing 'versions' object.");
            }

            JsonObject versions = existingRoot.getAsJsonObject("versions");

            if ("latest".equals(versionKey)) {
                Map.Entry<String, JsonElement> firstEntry =
                        versions.entrySet().iterator().next();

                return GSON.fromJson(firstEntry.getValue(), VersionInfo.class);
            }

            if (!versions.has(versionKey)) {
                throw new IllegalArgumentException("Version key not found: " + versionKey);
            }

            return GSON.fromJson(versions.get(versionKey), VersionInfo.class);

        } catch (Exception e) {
            LOGGER.severe("Failed to get version info: " + e.getMessage());
            throw new RuntimeException("Could not get version info for key: " + versionKey, e);
        }
    }

    private static Optional<Map<String, VersionInfo>> getVersionOptional() {
        try {
            JsonArray jsonArray = getJsonArray();
            return Optional.of(parseAndSortVersions(jsonArray));
        } catch (Exception e) {
            LOGGER.severe("Error fetching version data: " + e.getMessage());
            return Optional.empty();
        }
    }

    // opens the URL and parses the response as a JsonArray
    private static JsonArray getJsonArray() throws IOException {
        URL url = URI.create(VERSIONS_URL).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new IOException("HTTP error code: " + conn.getResponseCode());
        }

        try (InputStreamReader reader = new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)) {
            return JsonParser.parseReader(reader).getAsJsonArray();
        } finally {
            conn.disconnect();
        }
    }

    private static Map<String, VersionInfo> parseAndSortVersions(JsonArray jsonArray) {
        Map<String, VersionInfo> versionMap = new HashMap<>();

        jsonArray.forEach(element -> {
            JsonObject obj = element.getAsJsonObject();
            String id = obj.get("id").getAsString();

            int dataPackVersion = obj.get("data_pack_version").getAsInt();

            int dataPackVersionMinor = obj.has("data_pack_version_minor")
                    ? obj.get("data_pack_version_minor").getAsInt()
                    : -1;

            int resourcePackVersion = obj.get("resource_pack_version").getAsInt();

            int resourcePackVersionMinor = obj.has("resource_pack_version_minor")
                    ? obj.get("resource_pack_version_minor").getAsInt()
                    : -1;

            int dataVersion = obj.get("data_version").getAsInt();

            versionMap.put(id, new VersionInfo(
                    dataPackVersion,
                    dataPackVersionMinor,
                    resourcePackVersion,
                    resourcePackVersionMinor,
                    dataVersion
            ));
        });

        return versionMap.entrySet().stream()
                .sorted(Comparator.comparingInt(
                        (Map.Entry<String, VersionInfo> e) -> e.getValue().dataVersion()
                ).reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

}
