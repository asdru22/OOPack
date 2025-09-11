package com.asdru.oopack.objects.assets;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.AssetsJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Blockstate extends AssetsJson {
  public static final JsonFileFactory<Blockstate> f = new JsonFile.Factory<>(Blockstate.class);

  protected Blockstate(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "blockstates";
  }
}
