package com.asdru.oopack.objects;

import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.util.JsonUtils;
import com.google.gson.JsonObject;

import java.nio.file.Path;
import java.util.UUID;


public abstract sealed class JsonFile extends AbstractFile<JsonObject> permits DataJson, AssetsJson {

    private Object[] args = {};

    public JsonFile(String name, String content) {
        super(name, JsonUtils.toJson(content) );
    }

    public JsonFile(String name, String content, Object... args) {
        this(name, content);
        this.args = args;
    }

    public JsonFile(String content) {
        super(UUID.randomUUID().
                toString().
                replace("-", ""),
                JsonUtils.toJson(content));
    }

    public JsonFile(String content, Object... args) {
        this(content);
        this.args = args;
    }

    public JsonFile(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public void writeContent(Path path)  {
        JsonObject jsonContent = getContent();
        String jsonString = JsonUtils.toString(jsonContent);
        if(args.length > 0) {
            jsonString = String.format(jsonString, args);
        }
        FileUtils.createGenericTextFile(path,jsonString);
    }

    @Override
    public String getExtension() {
        return "json";
    }
}
