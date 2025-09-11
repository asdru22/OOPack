package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Recipes extends DataJson {
  public static final JsonFileFactory<Recipes> f = new JsonFile.Factory<>(Recipes.class);

  protected Recipes(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "recipe";
  }
}
