package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Font extends AssetsJson {
  public static final JsonFileFactory<Font> f = new JsonFile.Factory<>(Font.class);

  protected Font(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "font";
  }
}
