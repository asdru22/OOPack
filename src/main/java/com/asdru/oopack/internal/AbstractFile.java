package com.asdru.oopack.internal;

import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.Project;

import java.io.IOException;
import java.nio.file.Path;

public abstract class AbstractFile<T> implements FileSystemObject, PackFolder, Extension {
    private final String name;
    private T content;
    private FileSystemObject parent;
    private String namespaceId;
    private Project project;

    protected AbstractFile(String name, T content) {
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

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public abstract void writeContent(Path path) throws IOException;

    protected void setContent(T content) {
        this.content = content;
    }
}

