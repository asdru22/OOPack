package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class FunctionTag extends DataJson {

    public static final FunctionTag.Factory<FunctionTag> FACTORY =
            new JsonFile.Factory<>(FunctionTag.class);

    protected FunctionTag(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public String getFolderName() {
        return "tags/function";
    }
}
