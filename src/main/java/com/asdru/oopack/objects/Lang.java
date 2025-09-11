package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Lang extends AssetsJson {

    public static final JsonFileFactory<Lang> f =
            new JsonFile.Factory<>(Lang.class);

    protected Lang(String name, JsonObject content) {
        super(name, content);
    }


    @Override
    public String getFolderName() {
        return "lang";
    }
}
