package com.asdru.oopack;

import java.nio.file.Path;

public class Context {
    private static Context instance;
    public Path path;
    private final Path root;
    private Folder parent = null;

    public Context(Folder src) {
        this.path = src.getBuildPath();
        this.root = path;
        instance = this;
    }

    public void push(String path) {
        this.path = this.path.resolve(path);
    }

    public void pop(){
        this.path = this.path.getParent();
    }

    public static Context getInstance() {
        return instance;
    }

    public Path getPath() {
        return path;
    }

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
    }

    public Path getRoot() {
        return root;
    }
}
