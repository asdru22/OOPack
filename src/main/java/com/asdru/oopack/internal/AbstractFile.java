package com.asdru.oopack.internal;

abstract class AbstractFile<T> implements FileSystemObject {
    private final String name;
    private final T content;

    public AbstractFile(String name, T content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("file=[Name: %s\nContent:  %s]",
                name,content);
    }
}

