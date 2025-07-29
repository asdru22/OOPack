package com.asdru.oopack.internal;

import java.nio.file.Path;

public abstract class AbstractFile implements FileSystemObject {
    private Path path;

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path.toString();
    }

}
