package com.asdru.oopack.internal;

import java.nio.file.Path;

abstract class AbstractFile<T> implements FileSystemObject, PackFolder, Extension {
    private final String name;
    private final T content;

    public AbstractFile(String name, T content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        return name+": {"+content+"} ";
    }

    @Override
    public void build(Path parent) {
        Path path = parent.resolve(this.getFolderName()).resolve(name+this.getExtension());
        System.out.println(this);
    }
}

