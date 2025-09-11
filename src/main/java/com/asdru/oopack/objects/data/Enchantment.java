package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Enchantment extends DataJson {
  public static final JsonFileFactory<Enchantment> f = new JsonFile.Factory<>(Enchantment.class);

  protected Enchantment(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "enchantment";
  }
}
