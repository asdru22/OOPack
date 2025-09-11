package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Worldgen extends DataJson {
  public static final JsonFileFactory<Worldgen> f = new JsonFile.Factory<>(Worldgen.class);

  protected Worldgen(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "worldgen";
  }
}
