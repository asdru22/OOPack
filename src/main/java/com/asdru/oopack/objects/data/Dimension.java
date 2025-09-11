package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Dimension extends DataJson {
  public static final JsonFileFactory<Dimension> f = new JsonFile.Factory<>(Dimension.class);

  protected Dimension(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "dimension";
  }
}
