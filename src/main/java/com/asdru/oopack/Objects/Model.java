package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class Model extends AssetsJson {

    public Model(String name, String content) {
        super(name, content);
    }

    public Model(String name, String content, Object... args) {
        super(name, content, args);
    }

    public Model(String content) {
        super(content);
    }

    public Model(String content, Object... args) {
        super(content, args);
    }

    public Model(String name, JsonObject content) {
        super(name, content);
    }


    @Override
    public String getFolderName() {
        return "model";
    }
}
