package com.asdru.oopack.objects;

import com.asdru.oopack.internal.PlainFile;
import com.google.gson.JsonObject;

public non-sealed class LootTable extends DataJson {

    public static final Model.Factory FACTORY = new Model.Factory();

    public LootTable(String name, JsonObject content) {
        super(name, content);
    }

    public LootTable(String name, JsonObject content, Object... args) {
        super(name, content, args);
    }

    public static class Factory extends PlainFile.Factory<LootTable> {
        public Factory() {
            super(LootTable.class);
        }
    }


    @Override
    public String getFolderName() {
        return "loot_table";
    }
}
