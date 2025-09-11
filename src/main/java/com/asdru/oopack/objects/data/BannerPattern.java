package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class BannerPattern extends DataJson {
  public static final JsonFileFactory<BannerPattern> f = new JsonFile.Factory<>(BannerPattern.class);

  protected BannerPattern(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "banner_pattern";
  }
}
