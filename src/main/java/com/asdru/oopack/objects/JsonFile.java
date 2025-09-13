package com.asdru.oopack.objects;

import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.util.IOUtils;
import com.asdru.oopack.util.JsonUtils;
import com.google.gson.JsonObject;

import java.nio.file.Path;


public abstract class JsonFile extends PlainFile<JsonObject> {

    protected JsonFile(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public void writeContent(Path path) {
        JsonObject jsonContent = getContent();
        String jsonString = JsonUtils.toString(jsonContent);

        IOUtils.createGenericTextFile(path, jsonString);
    }

    @Override
    public String getExtension() {
        return "json";
    }

    protected static class Factory<F extends JsonFile> extends PlainFile.Factory<F, JsonObject>
            implements JsonFileFactory<F> {

        public Factory(Class<F> clazz) {
            super(clazz);
        }

        // name + json content
        @Override
        public F of(Class<? extends F> clazz, String name, JsonObject json) {
            return new Factory<>(clazz).createInstance(name, json);
        }

        // random name + json content
        @Override
        public F of(Class<? extends F> clazz, JsonObject json) {
            return new Factory<>(clazz).createInstance(randomName(), json);
        }

        @Override
        protected F createInstance(String name, String content) {
            return instantiate(name, JsonUtils.toJson(content));
        }

        protected F createInstance(String name, JsonObject content) {
            return instantiate(name, content);
        }
    }
}
