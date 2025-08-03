package com.asdru.oopack.internal;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class TextFile extends AbstractFile<String> {

    protected TextFile(String name, String content) {
        super(name, content);
    }

    protected TextFile(String name, String content, Object... args) {
        this(name, String.format(content, args));
    }

    @Override
    public void writeContent(Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.writeString(path, getContent());
    }
}

