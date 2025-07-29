package com.asdru.oopack;

import com.asdru.oopack.internal.FileSystemObject;
import com.asdru.oopack.internal.Subfolder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Namespace implements FileSystemObject {
    private final String name;
    public Namespace(String name) {
        this.name = name;
    }

    private final List<Subfolder> subfolders = new ArrayList<>();

    public Namespace addSubfolder(Subfolder subfolder) {
        subfolders.add(subfolder);
        return this;
    }

    public String getName() {
        return name;
    }


    @Override
    public Object getContent() {
        return subfolders;
    }

    @Override
    public void build(Path parent) {
        subfolders.forEach(subfolder -> subfolder.build(parent.resolve(name)));
    }

    @Override
    public String toString() {
        return String.format("namespace=[Name: %s\nFolders:  %s]",
                name,subfolders);
    }
}
