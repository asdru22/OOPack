package com.asdru.oopack.objects;

import com.asdru.oopack.Namespace;
import com.google.gson.JsonObject;

sealed public abstract class AssetsJson extends JsonFile permits Model, Lang {

    protected AssetsJson(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        assets.add(this);
    }
}
