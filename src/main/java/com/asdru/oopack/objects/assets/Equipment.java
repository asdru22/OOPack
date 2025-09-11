package com.asdru.oopack.objects.assets;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.AssetsJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Equipment extends AssetsJson {
  public static final JsonFileFactory<Equipment> f = new JsonFile.Factory<>(Equipment.class);

  protected Equipment(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "equipment";
  }
}
