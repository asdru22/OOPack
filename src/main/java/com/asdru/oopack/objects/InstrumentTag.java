package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class InstrumentTag extends DataJson {
  public static final JsonFileFactory<InstrumentTag> f = new JsonFile.Factory<>(InstrumentTag.class);

  protected InstrumentTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/instrument";
  }
}
