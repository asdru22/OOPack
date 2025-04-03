package asdru.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Json {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static JsonObject parse(String jsonString) {
        return JsonParser.parseString(jsonString).getAsJsonObject();
    }

    public static String toString(JsonObject input) {
        return gson.toJson(input);
    }
}
