package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public non-sealed class LootTable extends DataJson {

    public LootTable(String name, String content) {
        super(name, content);
    }

    public LootTable(String name, String content, Object... args) {
        super(name, content, args);
    }

    public LootTable(String content) {
        super(content);
    }

    public LootTable(String content, Object... args) {
        super(content, args);
    }

    public LootTable(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public String getFolderName() {
        return "loot_table";
    }
}
