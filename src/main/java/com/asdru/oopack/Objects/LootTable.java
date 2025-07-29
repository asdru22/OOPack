package com.asdru.oopack.Objects;

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
}
