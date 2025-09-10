package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class Model extends AssetsJson {

    public static final JsonFile.Factory<Model> FACTORY =
            new JsonFile.Factory<>(Model.class);

    public Model(String name, JsonObject content) {
        super(name, content);
    }

    public Model(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }

    @Override
    public String getFolderName() {
        return "model";
    }
}
