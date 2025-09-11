package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class ItemTag extends DataJson {
  public static final JsonFileFactory<ItemTag> f = new JsonFile.Factory<>(ItemTag.class);

  protected ItemTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/item";
  }
}
