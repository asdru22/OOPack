package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;

public class SoundFile extends AssetsJson {
    public static final JsonFileFactory<SoundFile> f = new JsonFile.Factory<>(SoundFile.class);

    protected SoundFile(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public String getFolderName() {
        return "";
    }
}
