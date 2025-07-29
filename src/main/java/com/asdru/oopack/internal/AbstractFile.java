package com.asdru.oopack.internal;

public abstract class AbstractFile implements FileSystemObject {
    private final String name;
    private final String content;

    public AbstractFile(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("file=[Name: %s\nContent:  %s]",
                name,content);
    }
}

