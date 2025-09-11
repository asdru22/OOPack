package com.asdru.oopack.objects;

import com.google.gson.JsonObject;

public interface JsonFileFactory<F extends JsonFile> extends FileFactory<F> {

    F of(Class<? extends F> clazz, String name, JsonObject json);

    F of(Class<? extends F> clazz, JsonObject json);
}

