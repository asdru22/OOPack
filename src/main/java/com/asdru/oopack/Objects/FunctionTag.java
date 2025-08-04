package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class FunctionTag extends DataJson {

    public FunctionTag(String name, String content) {
        super(name, content);
    }

    public FunctionTag(String name, String content, Object... args) {
        super(name, content, args);
    }

    public FunctionTag(String content) {
        super(content);
    }

    public FunctionTag(String content, Object... args) {
        super(content, args);
    }

    public FunctionTag(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public String getFolderName() {
        return "tags/function";
    }
}
