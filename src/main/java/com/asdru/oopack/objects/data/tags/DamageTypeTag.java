package com.asdru.oopack.objects.data.tags;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class DamageTypeTag extends DataJson {
  public static final JsonFileFactory<DamageTypeTag> f = new JsonFile.Factory<>(DamageTypeTag.class);

  protected DamageTypeTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/damage_type";
  }
}
