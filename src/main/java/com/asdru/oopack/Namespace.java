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

    public Namespace add(FileSystemObject fso) {
        children.add(fso);
        return this;
    }

    public String getName() {
        return name;
    }


    // REMOVE LATER!!
    @Override
    public void collectByType(Namespace data, Namespace assets) {

    }

    @Override
    public List<FileSystemObject> getContent() {
        return children;
    }

    @Override
    public void build(Path parent) {
        children.forEach(fso -> fso.build(parent.resolve(name)));
    }

    @Override
    public String toString() {
        return String.format("namespace=[Name: %s\nFolders:  %s]",
                name, children);
    }
}
