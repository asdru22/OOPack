package com.asdru.oopack.objects;

import com.asdru.oopack.internal.PlainFile;
import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.util.JsonUtils;
import com.google.gson.JsonObject;

import java.nio.file.Path;


public abstract sealed class JsonFile extends PlainFile<JsonObject> permits DataJson, AssetsJson {

    protected JsonFile(String name, JsonObject content) {
        super(name, content);
    }

    protected JsonFile(String name, JsonObject content, Object... args) {
        this(name, content);
        this.args = args;
    }

    @Override
    public void writeContent(Path path) {
        JsonObject jsonContent = getContent();
        String jsonString = JsonUtils.toString(jsonContent);

        FileUtils.createGenericTextFile(path, jsonString);
    }

    @Override
    public String getExtension() {
        return "json";
    }
}
