package com.asdru.oopack.objects;

import com.asdru.oopack.internal.PlainFile;
import com.google.gson.JsonObject;

public non-sealed class Lang extends AssetsJson {

    public static final Model.Factory FACTORY = new Model.Factory();

    public Lang(String name, JsonObject content) {
        super(name, content);
    }

    public Lang(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }

    public static class Factory extends PlainFile.Factory<Lang> {
        public Factory() {
            super(Lang.class);
        }
    }


    @Override
    public String getFolderName() {
        return "lang";
    }
}
