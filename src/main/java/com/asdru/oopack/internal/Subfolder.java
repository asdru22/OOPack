package com.asdru.oopack.internal;

import java.nio.file.Path;

public class Subfolder implements FileSystemObject {
    private final String name;
    private FileSystemObject content; // Either FileObject or GenericFolder

    public Subfolder(String name) {
        this.name = name;
    }

    public Subfolder setContent(FileSystemObject content) {
        this.content = content;
        return this;
    }

    public FileSystemObject getContentObject() {
        return content;
    }


    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public void build(Path parent) {
        content.build(parent.resolve(name));
    }

    @Override
    public String toString() {
        return String.format("subfolder=[Name: %s\nContent:  %s]",
                name,content);
    }
}
