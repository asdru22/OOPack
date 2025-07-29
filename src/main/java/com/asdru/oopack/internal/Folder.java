package com.asdru.oopack.internal;

import java.util.List;

public class Folder extends AbstractFolder<FileSystemObject> {

    public Folder(String name) {
        super(name);

    }

    public Folder add(FileSystemObject child) {
        content.add(child);
        return this;
    }

    public List<FileSystemObject> get() {
        return content;
    }

}
