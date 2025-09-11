package com.asdru.oopack.objects.assets;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.AssetsJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Atlas extends AssetsJson {
  public static final JsonFileFactory<Atlas> f = new JsonFile.Factory<>(Atlas.class);

  protected Atlas(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "atlases";
  }
}
