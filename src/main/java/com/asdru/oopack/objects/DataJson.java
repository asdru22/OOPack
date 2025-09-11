package com.asdru.oopack.objects;

import com.asdru.oopack.Namespace;
import com.google.gson.JsonObject;

sealed abstract public class DataJson extends JsonFile
        permits LootTable, FunctionTag {

    protected DataJson(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        data.add(this);
    }
}
