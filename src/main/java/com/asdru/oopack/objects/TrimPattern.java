package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class TrimPattern extends DataJson {
  public static final JsonFileFactory<TrimPattern> f = new JsonFile.Factory<>(TrimPattern.class);

  protected TrimPattern(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "trim_pattern";
  }
}
