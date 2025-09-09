package com.asdru.oopack.objects;

import com.asdru.oopack.internal.PlainFile;
import com.google.gson.JsonObject;

public non-sealed class Model extends AssetsJson {
    public static final Factory FACTORY = new Factory();


    public Model(String name, JsonObject content) {
        super(name, content);
    }

    public Model(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }


    public static class Factory extends PlainFile.Factory<Model> {
        public Factory() {
            super(Model.class);
        }
    }

    @Override
    public String getFolderName() {
        return "model";
    }
}
