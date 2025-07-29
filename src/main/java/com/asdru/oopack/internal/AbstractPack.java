package com.asdru.oopack.internal;

import java.nio.file.Path;

public abstract class AbstractPack<T> implements FileSystemObject {
    Path path;
    T packContent;
    String mcmeta;

    @Override
    public Path getPath() {
        return null;
    }
}
