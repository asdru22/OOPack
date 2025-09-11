package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class InstrumentTag extends DataJson {
  public static final JsonFileFactory<InstrumentTag> f = new JsonFile.Factory<>(InstrumentTag.class);

  protected InstrumentTag(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "tags/instrument";
  }
}
