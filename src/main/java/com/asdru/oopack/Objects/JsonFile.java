package com.asdru.oopack.Objects;

import com.asdru.oopack.internal.TextFile;

import java.util.UUID;


public sealed class JsonFile extends TextFile
        permits DataJson, AssetsJson {

    public JsonFile(String name, String content) {
        super(String.format("%s.json",name), content);
    }

    public JsonFile(String name, String content, Object... args) {
        this(name, String.format(content, args));
    }

    public JsonFile(String content) {
        this(UUID.randomUUID().toString().replace("-", ""),content);
    }

    public JsonFile(String content, Object... args) {
        this(String.format(content, args));
    }
}
