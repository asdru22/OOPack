package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class DialogTag extends DataJson {
  public static final JsonFileFactory<DialogTag> f = new JsonFile.Factory<>(DialogTag.class);

  protected DialogTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/dialog";
  }
}
