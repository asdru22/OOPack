package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class Lang extends AssetsJson {

    public Lang(String name, String content) {
        super(name, content);
    }

    public Lang(String name, String content, Object... args) {
        super(name, content, args);
    }

    public Lang(String content) {
        super(content);
    }

    public Lang(String content, Object... args) {
        super(content, args);
    }

    public Lang(String name, JsonObject content) {
        super(name, content);
    }


    @Override
    public String getFolderName() {
        return "lang";
    }
}
