package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class Lang extends AssetsJson {

    public static final JsonFile.Factory<Lang> FACTORY =
            new JsonFile.Factory<>(Lang.class);

    protected Lang(String name, JsonObject content) {
        super(name, content);
    }


    @Override
    public String getFolderName() {
        return "lang";
    }
}
