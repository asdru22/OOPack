package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Item extends AssetsJson {
  public static final JsonFileFactory<Item> f = new JsonFile.Factory<>(Item.class);

  protected Item(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "items";
  }
}
