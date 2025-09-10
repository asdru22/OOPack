package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class Model extends AssetsJson {

    public static final JsonFile.Factory<Model> FACTORY =
            new JsonFile.Factory<>(Model.class);

    protected Model(String name, JsonObject content) {
        super(name, content);
    }


    @Override
    public String getFolderName() {
        return "model";
    }
}
