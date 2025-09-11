package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class Dialog extends DataJson {
  public static final JsonFileFactory<Dialog> f = new JsonFile.Factory<>(Dialog.class);

  protected Dialog(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "dialog";
  }
}
