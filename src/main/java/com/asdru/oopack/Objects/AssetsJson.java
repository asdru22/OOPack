package com.asdru.oopack.objects;

import com.asdru.oopack.Namespace;
import com.google.gson.JsonObject;

sealed public abstract class AssetsJson extends JsonFile permits Model, Lang {


    public AssetsJson(String name, JsonObject content) {
        super(name, content);
    }

    public AssetsJson(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        assets.add(this);
    }
}
