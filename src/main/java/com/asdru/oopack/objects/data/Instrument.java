package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Instrument extends DataJson {
  public static final JsonFileFactory<Instrument> f = new JsonFile.Factory<>(Instrument.class);

  protected Instrument(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "instrument";
  }
}
