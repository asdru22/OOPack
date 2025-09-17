package com.asdru.oopack.internal;

import com.asdru.oopack.objects.JsonFile;
import com.google.gson.JsonObject;

import java.nio.file.Path;

public interface JsonFileFactory<F extends JsonFile> extends FileFactory<F> {
    F ofName(String name, JsonObject content);
    F of(JsonObject json);
    F ofFile(String path);
    F ofFile(String path, String name);
}

