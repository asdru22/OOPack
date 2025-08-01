package com.asdru.oopack.internal;

import java.util.List;

public class Folder extends AbstractFolder<FileSystemObject> {

    public Folder(String name) {
        super(name);
    }

    public FileSystemObject add(FileSystemObject child) {
        content.add(child);
        return child;
    }

    public FileSystemObject[] add(FileSystemObject... children) {
        content.addAll(List.of(children)); // Java 9+
        return children;
    }

    public List<FileSystemObject> get() {
        return content;
    }
}
