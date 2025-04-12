package oopack.json;

import oopack.Buildable;
import oopack.Loggable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class JsonDict extends HashMap<String, String> implements Buildable, Loggable {

    @Override
    public void build(Path buildPath) {
        makeFile(buildPath);
    }

    protected void makeFile(Path buildPath) {
        try {
            Files.createDirectories(buildPath.getParent());
            Files.writeString(buildPath, Json.toJson(this));
        } catch (IOException e) {
            logger().severe("Failed to write JSON to: " + buildPath + " - " + e.getMessage());
        }
    }
}

