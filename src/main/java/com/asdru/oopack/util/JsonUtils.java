package com.asdru.oopack.util;

import com.google.gson.*;

public class JsonUtils {

    // Static Gson instance with pretty printing
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    public static String toString(JsonObject obj) {
        if (obj == null) {
            throw new IllegalArgumentException("JsonObject cannot be null");
        }
        return GSON.toJson(obj);
    }

    public static JsonObject toJson(String jsonString) {
        if (jsonString == null || jsonString.trim().isEmpty()) {
            throw new IllegalArgumentException("Input JSON string cannot be null or empty");
        }

        try {
            return JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Malformed JSON string", e);
        } catch (IllegalStateException e) {
            throw new IllegalArgumentException("Provided JSON is not a JsonObject", e);
        }
    }
}
