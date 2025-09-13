package com.asdru.oopack.objects.data.tags;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class FunctionTag extends DataJson {
  public static final JsonFileFactory<FunctionTag> f = new JsonFile.Factory<>(FunctionTag.class);

  protected FunctionTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/function";
  }
}
