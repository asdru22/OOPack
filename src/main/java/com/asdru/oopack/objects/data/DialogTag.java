package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class DialogTag extends DataJson {
  public static final JsonFileFactory<DialogTag> f = new JsonFile.Factory<>(DialogTag.class);

  protected DialogTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/dialog";
  }
}
