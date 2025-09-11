package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class ItemTag extends DataJson {
  public static final JsonFileFactory<ItemTag> f = new JsonFile.Factory<>(ItemTag.class);

  protected ItemTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/item";
  }
}
