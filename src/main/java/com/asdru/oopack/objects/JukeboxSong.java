package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public non-sealed class JukeboxSong extends DataJson {
  public static final JsonFileFactory<JukeboxSong> f = new JsonFile.Factory<>(JukeboxSong.class);

  protected JukeboxSong(String name, JsonObject content) {
    super(name, content);
  }

  @Override
  public String getFolderName() {
    return "jukebox_song";
  }
}
