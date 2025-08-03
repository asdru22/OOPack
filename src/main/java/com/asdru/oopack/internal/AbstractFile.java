package com.asdru.oopack.internal;

import com.asdru.oopack.FileUtils;

import java.io.IOException;
import java.nio.file.Path;

public abstract class AbstractFile<T> implements FileSystemObject, PackFolder, Extension {
    private final String name;
    private final T content;
    private FileSystemObject parent;
    private String namespaceId;

    public AbstractFile(String name, T content) {
        this.name = name;
        this.content = content;
    }

    public void setNamespaceId(String ns) {
        namespaceId = ns;
    }

    public String getNamespaceId() {
        return namespaceId;
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
        return String.format("%s:%s", getNamespaceId(), getName());
    }

    @Override
    public void build(Path parent) {
        FileUtils.createFile(this,
                parent.resolve(this.getFolderName()).resolve(name+"."+this.getExtension()));
    }

    public abstract void writeContent(Path path) throws IOException;
}

