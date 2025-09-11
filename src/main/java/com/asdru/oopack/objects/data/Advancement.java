package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Advancement extends DataJson {
  public static final JsonFileFactory<Advancement> f = new JsonFile.Factory<>(Advancement.class);

  protected Advancement(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "advancement";
  }
}
