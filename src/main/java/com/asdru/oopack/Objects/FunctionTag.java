package com.asdru.oopack.objects;

import com.asdru.oopack.internal.PlainFile;
import com.google.gson.JsonObject;

public non-sealed class FunctionTag extends DataJson {

    public static final Model.Factory FACTORY = new Model.Factory();

    public FunctionTag(String name, JsonObject content) {
        super(name, content);
    }

    public FunctionTag(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }

    public static class Factory extends PlainFile.Factory<FunctionTag> {
        public Factory() {
            super(FunctionTag.class);
        }
    }


    @Override
    public String getFolderName() {
        return "tags/function";
    }
}
