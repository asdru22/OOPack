package com.asdru.oopack.objects.data.tags;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class BannerPatternTag extends DataJson {
  public static final JsonFileFactory<BannerPatternTag> f = new JsonFile.Factory<>(BannerPatternTag.class);

  protected BannerPatternTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/banner_pattern";
  }
}
