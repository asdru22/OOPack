package com.asdru.oopack.objects.assets;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.AssetsJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Lang extends AssetsJson {
  public static final JsonFileFactory<Lang> f = new JsonFile.Factory<>(Lang.class);

  protected Lang(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "lang";
  }
}
