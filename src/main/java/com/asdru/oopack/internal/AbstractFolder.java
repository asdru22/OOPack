package com.asdru.oopack.internal;

import com.asdru.oopack.Namespace;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFolder <T extends FileSystemObject> implements FileSystemObject {
    protected final List<T> children = new ArrayList<>();
    private FileSystemObject parent;

    @Override
    public void build(Path parent) {
        children.forEach(f -> f.build(parent));
    }

    @Override
    public List<T> getContent() {
        return children;
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
    public String toString() {
        return children.toString();
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        for (FileSystemObject fso : children) {
            fso.collectByType(data, assets);  // polymorphic call
        }
    }

}
