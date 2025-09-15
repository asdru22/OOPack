package com.asdru.oopack.internal;

import com.asdru.oopack.Namespace;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractFolder<T extends FileSystemObject> implements FileSystemObject {
    protected final Set<T> children = new LinkedHashSet<>();
    protected ContextItem parent;

    @Override
    public void build(Path parent) {
        children.forEach(f -> f.build(parent));
    }

    @Override
    public Set<T> getContent() {
        return children;
    }

    @Override
    public ContextItem getParent() {
        return parent;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        children.forEach(f -> builder.append(f.toString()));
        return builder.toString();
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        for (FileSystemObject fso : children) {
            fso.collectByType(data, assets);  // polymorphic call
        }
    }

}
