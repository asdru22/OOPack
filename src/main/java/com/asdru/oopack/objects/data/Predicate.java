package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Predicate extends DataJson {
  public static final JsonFileFactory<Predicate> f = new JsonFile.Factory<>(Predicate.class);

  protected Predicate(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "predicate";
  }
}
