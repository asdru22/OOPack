package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class TrimMaterial extends DataJson {
  public static final JsonFileFactory<TrimMaterial> f = new JsonFile.Factory<>(TrimMaterial.class);

  protected TrimMaterial(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "trim_material";
  }
}
