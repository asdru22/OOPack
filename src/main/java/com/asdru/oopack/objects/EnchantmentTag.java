package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class EnchantmentTag extends DataJson {
  public static final JsonFileFactory<EnchantmentTag> f = new JsonFile.Factory<>(EnchantmentTag.class);

  protected EnchantmentTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/enchantment";
  }
}
