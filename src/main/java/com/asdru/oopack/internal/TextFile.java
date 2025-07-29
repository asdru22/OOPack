package com.asdru.oopack.internal;

import java.nio.file.Path;

public class TextFile extends AbstractFile<String> {

    public TextFile(String name, String content) {
        super(name, content);
    }

    @Override
    public void build(Path parent) {

    }
}

