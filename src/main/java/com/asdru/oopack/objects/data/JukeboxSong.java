package com.asdru.oopack.objects.data;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.DataJson;
import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

public class JukeboxSong extends DataJson {
  public static final JsonFileFactory<JukeboxSong> f = new JsonFile.Factory<>(JukeboxSong.class);

  protected JukeboxSong(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "jukebox_song";
  }
}
