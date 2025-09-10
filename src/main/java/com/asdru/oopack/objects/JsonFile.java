package com.asdru.oopack.objects;

import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.util.JsonUtils;
import com.google.gson.JsonObject;

import java.nio.file.Path;


public abstract sealed class JsonFile extends PlainFile<JsonObject> permits DataJson, AssetsJson {

    public static final PlainFile.Factory<JsonFile, JsonObject> FACTORY =
            new PlainFile.Factory<>(JsonFile.class);

    protected JsonFile(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public void writeContent(Path path) {
        JsonObject jsonContent = getContent();
        String jsonString = JsonUtils.toString(jsonContent);

        FileUtils.createGenericTextFile(path, jsonString);
    }

    @Override
    public String getExtension() {
        return "json";
    }

    public static class Factory<F extends JsonFile> extends PlainFile.Factory<F, JsonObject> {

        public Factory(Class<F> clazz) {
            super(clazz);
        }

        // name + json content
        public <T extends JsonFile> T of(Class<T> clazz, String name, JsonObject json) {
            return new Factory<>(clazz).createInstance(name, json);
        }

        // random name + json content
        public <T extends JsonFile> T of(Class<T> clazz, JsonObject json) {
            return new Factory<>(clazz).createInstance(randomName(), json);
        }

        @Override
        protected F createInstance(String name, String content) {
            try {
                // Convert String to JsonObject first
                return clazz.getDeclaredConstructor(String.class, JsonObject.class)
                        .newInstance(name, JsonUtils.toJson(content));
            } catch (Exception e) {
                throw new RuntimeException("Failed to create JsonFile instance", e);
            }
        }

        protected F createInstance(String name, JsonObject content) {
            try {
                return clazz.getDeclaredConstructor(String.class, JsonObject.class)
                        .newInstance(name, content);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create JsonFile instance", e);
            }
        }
    }
}
