package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public class Model extends AssetsJson {

    public static final JsonFileFactory<Model> f =
            new JsonFile.Factory<>(Model.class);

    protected Model(String name, JsonObject content) {
        super(name, content);
    }


    @Override
    public String getFolderName() {
        return "model";
    }
}
