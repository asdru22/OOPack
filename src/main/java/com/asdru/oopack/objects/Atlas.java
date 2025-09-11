package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Atlas extends AssetsJson {
  public static final JsonFileFactory<Atlas> f = new JsonFile.Factory<>(Atlas.class);

  protected Atlas(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "atlases";
  }
}
