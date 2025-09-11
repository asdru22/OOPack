package com.asdru.oopack.objects.assets;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.AssetsJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Font extends AssetsJson {
  public static final JsonFileFactory<Font> f = new JsonFile.Factory<>(Font.class);

  protected Font(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "font";
  }
}
