package com.asdru.oopack;

import com.asdru.oopack.internal.FileSystemObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Namespace implements FileSystemObject {
    private final String name;
    public Namespace(String name) {
        this.name = name;
    }

    private final List<FileSystemObject> children = new ArrayList<>();

    public void add(FileSystemObject fso) {
        children.add(fso);
    }

    public String getName() {
        return name;
    }

    public List<FileSystemObject> getContent() {
        return children;
    }

    @Override
    public String toString() {
        return String.format("%s{%s}", name, children);
    }

    @Override
    public void build(Path parent) {
        children.forEach(fso -> fso.build(parent.resolve(name)));
    }

    // Corner of shame
    @Override
    public void collectByType(Namespace data, Namespace assets) {}
    @Override
    public void setParent(FileSystemObject parent) {}
    @Override
    public FileSystemObject getParent() {return null;}
}
