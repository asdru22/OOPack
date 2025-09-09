package com.asdru.oopack.objects;

import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.util.JsonUtils;
import com.google.gson.JsonObject;

import java.nio.file.Path;
import java.util.UUID;


public abstract sealed class JsonFile extends AbstractFile<JsonObject> permits DataJson, AssetsJson {

    private Object[] args = {};

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
