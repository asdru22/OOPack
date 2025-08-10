package com.asdru.oopack.util;

import com.asdru.oopack.internal.VersionInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class VersionUtils {

    private static final String VERSIONS_URL =
            "https://raw.githubusercontent.com/misode/mcmeta/refs/heads/summary/versions/data.json";

    public static Optional<Map<String, VersionInfo>> getVersion() {
        try {
            JsonArray jsonArray = fetchJsonArrayFromUrl();
            return Optional.of(parseAndSortVersions(jsonArray));
        } catch (Exception e) {
            System.err.println("Error fetching version data: " + e.getMessage());
            return Optional.empty();
        }
    }

    // Opens the URL and parses the response as a JsonArray
    private static JsonArray fetchJsonArrayFromUrl() throws IOException {
        URL url = URI.create(VERSIONS_URL).toURL(); // Usa URI.create() per URL remoti
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
