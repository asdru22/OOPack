package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class BlockTag extends DataJson {
  public static final JsonFileFactory<BlockTag> f = new JsonFile.Factory<>(BlockTag.class);

  protected BlockTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/block";
  }
}
