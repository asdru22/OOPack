package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Advancement extends DataJson {
  public static final JsonFileFactory<Advancement> f = new JsonFile.Factory<>(Advancement.class);

  protected Advancement(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "advancement";
  }
}
