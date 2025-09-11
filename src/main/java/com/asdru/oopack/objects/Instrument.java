package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class Instrument extends DataJson {
  public static final JsonFileFactory<Instrument> f = new JsonFile.Factory<>(Instrument.class);

  protected Instrument(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "instrument";
  }
}
