package com.asdru.oopack.Objects;

sealed class DataJson extends JsonFile permits LootTable {
    public DataJson(String name, String content) {
        super(name, content);
    }

    public DataJson(String name, String content, Object... args) {
        super(name, content, args);
    }

    public DataJson(String content) {
        super(content);
    }

    public DataJson(String content, Object... args) {
        super(content, args);
    }
}
