package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class TrimMaterial extends DataJson {
  public static final JsonFileFactory<TrimMaterial> f = new JsonFile.Factory<>(TrimMaterial.class);

  protected TrimMaterial(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "trim_material";
  }
}
