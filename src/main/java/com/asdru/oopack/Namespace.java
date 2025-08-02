package com.asdru.oopack;

import com.asdru.oopack.internal.Buildable;
import com.asdru.oopack.internal.FileSystemObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Namespace implements Buildable {
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
        return String.format("namespace=[Name: %s\nFolders:  %s]",
                name, children);
    }

    @Override
    public void build(Path parent) {
        children.forEach(fso -> fso.build(parent.resolve(name)));
    }
}
