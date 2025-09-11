package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class PaintingVariantTag extends DataJson {
  public static final JsonFileFactory<PaintingVariantTag> f = new JsonFile.Factory<>(PaintingVariantTag.class);

  protected PaintingVariantTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/painting_variant";
  }
}
