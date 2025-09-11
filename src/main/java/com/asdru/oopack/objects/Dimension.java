package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Dimension extends DataJson {
  public static final JsonFileFactory<Dimension> f = new JsonFile.Factory<>(Dimension.class);

  protected Dimension(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "dimension";
  }
}
