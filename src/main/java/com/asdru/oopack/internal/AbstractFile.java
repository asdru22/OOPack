package com.asdru.oopack.internal;

import com.asdru.oopack.Context;
import com.asdru.oopack.Namespace;
import com.asdru.oopack.util.IOUtils;

import java.io.IOException;
import java.nio.file.Path;

public abstract class AbstractFile<T> implements FileSystemObject, PackFolder, Extension {
    private String name;
    private T content;
    private final ContextItem parent;
    private final Namespace namespace;

    protected AbstractFile(String name, T content) {
        this.name = name;
        this.content = content;
        this.namespace = Context.getActiveNamespace();
        this.parent = Context.peek();
    }


    public String getNamespaceId() {
        return namespace.getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ContextItem getParent() {
        return parent;
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
        IOUtils.createFile(this,
                parent.resolve(this.getFolderName()).resolve(name + "." + this.getExtension()));
    }


    public abstract void writeContent(Path path) throws IOException;

    protected void setContent(T content) {
        this.content = content;
    }
}

