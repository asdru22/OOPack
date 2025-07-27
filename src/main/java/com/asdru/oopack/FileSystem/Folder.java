package com.asdru.oopack.FileSystem;

import com.asdru.oopack.Buildable;

import java.util.ArrayList;
import java.util.List;

public abstract class Folder implements Buildable {
    private final List<Buildable> children;

    public Folder() {
        this.children = new ArrayList<>();
    }

    public Folder(List<Buildable> children) {
        this.children = children;
    }

    public void addChild(Buildable child) {
        children.add(child);
    }

    public List<Buildable> getChildren() {
        return children;
    }
}