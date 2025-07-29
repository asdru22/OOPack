package com.asdru.oopack.internal;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFolder <T extends FileSystemObject> implements FileSystemObject {
    private final String name;
    private final List<T> files = new ArrayList<>();

    public AbstractFolder(String name) {
        this.name = name;
    }

    public void addFile(T file) {
        files.add(file);
    }

    public List<T> getFiles() {
        return files;
    }

    @Override
    public Object getContent() {
        return files;
    }
}
