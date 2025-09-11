package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Equipment extends AssetsJson {
  public static final JsonFileFactory<Equipment> f = new JsonFile.Factory<>(Equipment.class);

  protected Equipment(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "equipment";
  }
}
