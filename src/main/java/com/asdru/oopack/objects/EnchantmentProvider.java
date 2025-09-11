package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class EnchantmentProvider extends DataJson {
  public static final JsonFileFactory<EnchantmentProvider> f = new JsonFile.Factory<>(EnchantmentProvider.class);

  protected EnchantmentProvider(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "enchantment_provider";
  }
}
