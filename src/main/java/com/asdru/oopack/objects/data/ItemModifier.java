package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class ItemModifier extends DataJson {
  public static final JsonFileFactory<ItemModifier> f = new JsonFile.Factory<>(ItemModifier.class);

  protected ItemModifier(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "item_modifier";
  }
}
