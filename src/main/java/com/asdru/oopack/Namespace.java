package com.asdru.oopack;

import com.asdru.oopack.internal.FileSystemObject;
import com.asdru.oopack.internal.Folder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Namespace implements FileSystemObject {
    private final String name;
    public Namespace(String name) {
        this.name = name;
    }

    private final List<Folder> folders = new ArrayList<>();

    public Namespace addFolder(Folder folder) {
        folders.add(folder);
        return this;
    }

    public String getName() {
        return name;
    }


    @Override
    public Object getContent() {
        return folders;
    }

    @Override
    public void build(Path parent) {
        folders.forEach(folder -> folder.build(parent.resolve(name)));
    }

    @Override
    public String toString() {
        return String.format("namespace=[Name: %s\nFolders:  %s]",
                name, folders);
    }
}
