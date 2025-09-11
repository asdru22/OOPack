package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Worldgen extends DataJson {
  public static final JsonFileFactory<Worldgen> f = new JsonFile.Factory<>(Worldgen.class);

  protected Worldgen(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "worldgen";
  }
}
