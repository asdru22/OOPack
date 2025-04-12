package oopack.version;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import oopack.Loggable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Version implements Loggable {
    private static final String URL_STRING =
            "https://raw.githubusercontent.com/misode/mcmeta/refs/heads/summary/versions/data.json";

    public static Optional<Map<String, VersionInfo>> getDict() {
        try {
            URL url = URI.create(URL_STRING).toURL(); // Usa URI.create() per URL remoti
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            br.close();
            conn.disconnect();

            // Parse JSON
            JsonArray jsonArray = JsonParser.parseString(response.toString()).getAsJsonArray();

            // Wrap the map in an Optional
            return Optional.of(getStringVersionInfoMap(jsonArray));

        } catch (Exception e) {
            System.err.println("Error when getting dict - " + e.getMessage());

        }
        return Optional.empty();
    }

    private static Map<String, VersionInfo> getStringVersionInfoMap(JsonArray jsonArray) {
        Map<String, VersionInfo> versionMap = getVersionInfoMap(jsonArray);

        // Descending order
        return versionMap.entrySet()
                .stream()
                .sorted(Comparator.comparingInt((Map.Entry<String, VersionInfo> entry) ->
                        entry.getValue().dataVersion()).reversed()) // Sort by dataVersion (descending)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, _) -> e1, LinkedHashMap::new // Preserve order
                ));
    }

    private static Map<String, VersionInfo> getVersionInfoMap(JsonArray jsonArray) {
        Map<String, VersionInfo> versionMap = new HashMap<>();

        for (var element : jsonArray) {
            JsonObject obj = element.getAsJsonObject();
            String id = obj.get("id").getAsString();
            int dataPackVersion = obj.get("data_pack_version").getAsInt();
            int resourcePackVersion = obj.get("resource_pack_version").getAsInt();
            int dataVersion = obj.get("data_version").getAsInt();

            versionMap.put(id, new VersionInfo(dataPackVersion, resourcePackVersion, dataVersion));
        }
        return versionMap;
    }
}
