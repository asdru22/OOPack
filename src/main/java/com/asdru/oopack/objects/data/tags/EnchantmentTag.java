package com.asdru.oopack.objects.data.tags;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class EnchantmentTag extends DataJson {
  public static final JsonFileFactory<EnchantmentTag> f = new JsonFile.Factory<>(EnchantmentTag.class);

  protected EnchantmentTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/enchantment";
  }
}
