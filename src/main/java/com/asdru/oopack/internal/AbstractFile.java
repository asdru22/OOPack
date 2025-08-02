package com.asdru.oopack.internal;

import java.nio.file.Path;

abstract class AbstractFile<T> implements FileSystemObject, PackFolder, Extension {
    private final String name;
    private final T content;
    private FileSystemObject parent;
    private String namespaceid;

    public AbstractFile(String name, T content) {
        this.name = name;
        this.content = content;
    }

    public void setNamespaceId(String ns) {
        namespaceid = ns;
    }

    public String getNamespaceId() {
        return namespaceid;
    }

    public String getName() {
        return name;
    }

    @Override
    public FileSystemObject getParent() {
        return parent;
    }

    @Override
    public void setParent(FileSystemObject parent) {
        this.parent = parent;
    }

    @Override
    public T getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("%s:%s{%s}", getNamespaceId(), getName(),getContent());
    }

    @Override
    public void build(Path parent) {
        Path path = parent.resolve(this.getFolderName()).resolve(name+this.getExtension());
    }
}

