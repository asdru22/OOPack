package com.asdru.oopack.internal;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractFolder <T extends FileSystemObject> implements FileSystemObject {
    protected final String name;
    protected final List<T> content = new ArrayList<>();

    public AbstractFolder(String name) {
        this.name = name;
    }


    @Override
    public void build(Path parent) {
        content.forEach(f -> f.build(parent));
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("folder=[Name: %s\nContent:  %s]",
                name,content);
    }
}
