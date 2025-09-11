package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class DamageTypeTag extends DataJson {
  public static final JsonFileFactory<DamageTypeTag> f = new JsonFile.Factory<>(DamageTypeTag.class);

  protected DamageTypeTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/damage_type";
  }
}
