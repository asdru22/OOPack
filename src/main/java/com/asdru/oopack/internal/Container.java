package com.asdru.oopack.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Container<T extends FileSystemObject> implements FileSystemObject{

    protected List<T> children = new ArrayList<>();

    protected void add(T child) {
        children.add(child);
    }

    @SuppressWarnings("unchecked")
    protected void add(T... children) {
        Collections.addAll(this.children, children);
    }
}
