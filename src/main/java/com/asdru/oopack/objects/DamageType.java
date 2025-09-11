package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class DamageType extends DataJson {
  public static final JsonFileFactory<DamageType> f = new JsonFile.Factory<>(DamageType.class);

  protected DamageType(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "damage_type";
  }
}
