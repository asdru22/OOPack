package com.asdru.oopack.internal;


import com.asdru.oopack.objects.JsonFile;
import com.asdru.oopack.util.JsonUtils;
import com.google.gson.JsonObject;

import java.util.UUID;

public abstract class PlainFile<T> extends AbstractFile<T> {

    protected Object[] args = {};

    protected PlainFile(String name, T content) {
        super(name, content);
    }

    protected PlainFile(String name, T content, Object... args) {
        this(name, content);
        this.args = args;
    }


    protected static String randomName() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static String formatContent(String content, Object... args) {
        return args.length > 0 ? content.formatted(args) : content;
    }

    // name + String content
    public static <T extends PlainFile<T>> T of(Class<T> clazz, String name, String content) {
        return createInstance(clazz, name, JsonUtils.toJson(content));
    }

    // name + String content + args
    public static <T extends PlainFile<T>> T of(Class<T> clazz, String name, String content, Object... args) {
        return createInstance(clazz, name, JsonUtils.toJson(formatContent(content, args)), args);
    }

    // content only, random name
    public static <T extends PlainFile<T>> T of(Class<T> clazz, String content) {
        return createInstance(clazz, randomName(), JsonUtils.toJson(content));
    }

    // content + args, random name
    public static <T extends PlainFile<T>> T of(Class<T> clazz, String content, Object... args) {
        return createInstance(clazz, randomName(), JsonUtils.toJson(formatContent(content, args)), args);
    }

    // Internal helper to call constructor
    protected static <T extends PlainFile<T>> T createInstance(Class<T> clazz, String name, JsonObject json, Object... args) {
        try {
            if (args != null && args.length > 0) {
                return clazz.getConstructor(String.class, JsonObject.class, Object[].class)
                        .newInstance(name, json, args);
            } else {
                return clazz.getConstructor(String.class, JsonObject.class)
                        .newInstance(name, json);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create JsonFile instance", e);
        }
    }
}

