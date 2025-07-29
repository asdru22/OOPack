package com.asdru.oopack;

import com.asdru.oopack.internal.Container;

import java.nio.file.Path;

public class Assets extends Container<Namespace> {

    Path path;

    @Override
    public void build() {

    }

    @Override
    public Path getPath() {
        return path;
    }
}
