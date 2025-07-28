package com.asdru.oopack;

import java.nio.file.Path;

public class File implements FileSystemObject {

    Path buildPath;

    public File(String name) {
        Context ctx = Context.getInstance();
        this.buildPath = ctx.getPath().resolve(name);
        ctx.getParent().add(this);
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
        return buildPath.toString();
    }
}
