package com.asdru.oopack;

import com.asdru.oopack.internal.Buildable;

import java.awt.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class Pack implements Buildable {

    Image icon;
    List<Namespace> namespaces = new ArrayList<>() ;
    final String resourceName;

    public Pack(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public void build(Path parent) {
        namespaces.forEach(namespace -> namespace.build(parent.resolve(resourceName)));
    }

    protected void addNamespace(Namespace namespace) {
        namespaces.add(namespace);
    }

    @Override
    public String toString() {
        return String.format("%s: [%s]",resourceName,namespaces);
    }
}
