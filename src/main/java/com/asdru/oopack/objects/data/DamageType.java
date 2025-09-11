package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class DamageType extends DataJson {
  public static final JsonFileFactory<DamageType> f = new JsonFile.Factory<>(DamageType.class);

  protected DamageType(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "damage_type";
  }
}
