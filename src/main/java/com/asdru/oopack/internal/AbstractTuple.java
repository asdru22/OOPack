package com.asdru.oopack.internal;

import java.nio.file.Path;


public abstract class AbstractTuple<T, K> implements Buildable {
    private Path path;

    protected T t;
    protected K k;


    public T getT() {
        return t;
    }

    public K getK() {
        return k;
    }

    public Path getPath() {
        return path;
    }
}
