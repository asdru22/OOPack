package com.asdru.oopack.internal;

import java.nio.file.Path;

public class TextFile extends AbstractFile<String> {

    protected TextFile(String name, String content) {
        super(name, content);
    }

    protected TextFile(String name, String content, Object... args) {
        this(name, String.format(content, args));
    }

    @Override
    public void build(Path parent) {

    }
}

