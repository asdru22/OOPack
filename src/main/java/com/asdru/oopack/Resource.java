package com.asdru.oopack;

import com.asdru.oopack.fileSystem.Buildable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

abstract class Resource<T> implements Buildable {

    protected String name;
    protected List<T> resources;

    protected Resource(String name) {
        this.name = name;
        resources = new ArrayList<>();
    }

    protected void add(T resource) {
        resources.add(resource);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append("\n");
        for (T resource : resources) {
            sb.append(resource);
        }
        return sb.toString();
    }

}
