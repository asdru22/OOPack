package com.asdru.oopack;

import com.asdru.oopack.internal.Buildable;
import com.asdru.oopack.internal.Resource;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Pack implements Buildable {
    private final Project project;
    private Image icon;
    private final Map<String, Namespace> namespaces = new HashMap<>();
    private final Resource resource; // assets or data

    public Pack(Project project, Resource resource) {
        this.project = project;
        this.resource = resource;
    }

    @Override
    public void build(Path parent) {
        namespaces.values().forEach(namespace -> namespace.build(parent.resolve(resource.toString())));
    }

    protected void addNamespace(Namespace namespace) {
        namespaces.put(namespace.getName(),namespace);
    }

    public Namespace getNamespace(String name) {
        return namespaces.get(name);
    }

    @Override
    public String toString() {
        return String.format("%s: [%s]",resource,namespaces);
    }
}
