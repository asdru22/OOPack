package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class Lang extends AssetsJson {


    public Lang(String name, JsonObject content) {
        super(name, content);
    }

    public Lang(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }

    @Override
    public String getFolderName() {
        return "lang";
    }
}
