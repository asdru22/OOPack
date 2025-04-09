package oopack.json;

import oopack.Buildable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class JsonDict extends HashMap<String, String> implements Buildable {

    @Override
    public void build(Path buildPath) {
        makeFile(buildPath);
    }

    protected void makeFile(Path buildPath) {
        try {
            // Make sure the parent directory exists
            Files.createDirectories(buildPath.getParent());
            Files.writeString(buildPath, Json.toJson(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

