package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class LootTable extends DataJson {
    public LootTable(String name, JsonObject content) {
        super(name, content);
    }

    public LootTable(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }

    @Override
    public String getFolderName() {
        return "loot_table";
    }
}
