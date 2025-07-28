package com.asdru.oopack;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemObject {

    Path buildPath;
    List<FileSystemObject> children = new ArrayList<>();

    public Folder(String name) {
        Context ctx = Context.getInstance();
        ctx.push(name);
        ctx.setParent(this);
        buildPath = ctx.getPath();
    }

    public void add(FileSystemObject child) {
        children.add(child);
    }


    @Override
    public void build() {

    }

    @Override
    public Path getBuildPath() {
        return buildPath;
    }

    @Override
    public String toString() {
        return buildPath+"= {\n"+children+"\n}";
    }

}
