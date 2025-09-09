package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class FunctionTag extends DataJson {


    public FunctionTag(String name, JsonObject content) {
        super(name, content);
    }

    public FunctionTag(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }

    @Override
    public String getFolderName() {
        return "tags/function";
    }
}
