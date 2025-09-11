package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Recipes extends DataJson {
  public static final JsonFileFactory<Recipes> f = new JsonFile.Factory<>(Recipes.class);

  protected Recipes(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "recipe";
  }
}
