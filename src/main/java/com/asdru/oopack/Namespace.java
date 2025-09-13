package com.asdru.oopack;

import com.asdru.oopack.internal.Buildable;
import com.asdru.oopack.internal.ContextItem;
import com.asdru.oopack.internal.FileSystemObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Namespace implements Buildable, ContextItem {
    private final String name;

    protected Namespace(String name) {
        this.name = name;
    }

    public static Namespace of(String name) {
        Namespace ns = new Namespace(name);
        ns.enter();
        return ns;
    }

    private final List<FileSystemObject> children = new ArrayList<>();

    @Override
    public FileSystemObject add(FileSystemObject fso) {
        children.add(fso);
        return fso;
    }

    public String getName() {
        return name;
    }

    public List<FileSystemObject> getContent() {
        return children;
    }

    @Override
    public String toString() {
        return String.format("namespace(%s)", name);
    }

    @Override
    public void build(Path parent) {
        children.forEach(fso -> fso.build(parent.resolve(name)));
    }


    @Override
    public void exit() {
        Context.exit();
        Project.getInstance().addNamespace(this);
    }
}
