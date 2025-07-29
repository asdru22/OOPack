package com.asdru.oopack.internal;

import java.nio.file.Path;

public abstract class AbstractFolder extends Container<FileSystemObject> implements FileSystemObject {
    private Path path;

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", path, children);
    }
}
