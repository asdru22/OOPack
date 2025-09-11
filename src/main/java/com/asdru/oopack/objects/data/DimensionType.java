package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class DimensionType extends DataJson {
  public static final JsonFileFactory<DimensionType> f = new JsonFile.Factory<>(DimensionType.class);

  protected DimensionType(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "dimension_type";
  }
}
