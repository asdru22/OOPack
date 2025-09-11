package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class WorldgenTag extends DataJson {
  public static final JsonFileFactory<WorldgenTag> f = new JsonFile.Factory<>(WorldgenTag.class);

  protected WorldgenTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/worldgen";
  }
}
