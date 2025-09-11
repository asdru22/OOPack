package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class TrimPattern extends DataJson {
  public static final JsonFileFactory<TrimPattern> f = new JsonFile.Factory<>(TrimPattern.class);

  protected TrimPattern(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "trim_pattern";
  }
}
