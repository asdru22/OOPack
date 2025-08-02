package com.asdru.oopack.internal;

import com.asdru.oopack.Namespace;

import java.util.List;

public class Folder extends AbstractFolder<FileSystemObject> {

    private final Namespace parent;

    public Folder(Namespace parent){
        this.parent = parent;
        parent.add(this);
    }

    public FileSystemObject add(FileSystemObject child) {
        content.add(child);
        return child;
    }

    public FileSystemObject[] add(FileSystemObject... children) {
        content.addAll(List.of(children));
        return children;
    }

    public List<FileSystemObject> get() {
        return content;
    }
}
