package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class LootTable extends DataJson {
  public static final JsonFileFactory<LootTable> f = new JsonFile.Factory<>(LootTable.class);

  protected LootTable(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "loot_table";
  }
}
