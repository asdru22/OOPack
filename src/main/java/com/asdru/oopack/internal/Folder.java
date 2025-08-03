package com.asdru.oopack.internal;

import com.asdru.oopack.Namespace;


public class Folder extends AbstractFolder<FileSystemObject> {
    private final Namespace namespace;

    public Folder(Namespace parent){
        this.namespace = parent;   // save reference without type conflict
        parent.add(this);
    }

    public FileSystemObject add(FileSystemObject child) {
        child.setParent(this);

        // Walk up the tree to find the Namespace
        FileSystemObject current = this;
        while (current != null && !(current instanceof Namespace)) {
            current = current.getParent();
        }

        if (current instanceof Namespace ns && child instanceof AbstractFile<?> file) {
            file.setNamespaceId(ns.getName());
        }

        children.add(child);
        return child;
    }

    public FileSystemObject[] add(FileSystemObject... children) {
        for(FileSystemObject child : children){
            add(child);
        }
        return children;
    }
}
