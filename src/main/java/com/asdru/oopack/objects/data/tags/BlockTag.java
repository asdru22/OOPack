package com.asdru.oopack.objects.data.tags;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class BlockTag extends DataJson {
  public static final JsonFileFactory<BlockTag> f = new JsonFile.Factory<>(BlockTag.class);

  protected BlockTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/block";
  }
}
