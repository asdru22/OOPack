package com.asdru.oopack;

import com.asdru.oopack.internal.ContextItem;
import com.asdru.oopack.internal.FileSystemObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Namespace implements FileSystemObject, ContextItem {
    private final String name;
    private Project project;

    protected Namespace(Project project, String name) {
        this.name = name;
        this.project = project;
    }

    public static Namespace of(String name) {
        Project p = Project.getInstance();
        Namespace ns = new Namespace(p, name);
        p.getContext().push(ns);
        return ns;
    }

    private final List<FileSystemObject> children = new ArrayList<>();

    @Override
    public FileSystemObject add(FileSystemObject fso) {
        children.add(fso);
        fso.setProject(project);
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
        return String.format("%s{%s}", name, children);
    }

    @Override
    public void build(Path parent) {
        children.forEach(fso -> fso.build(parent.resolve(name)));
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public Project getProject() {
        return this.project;
    }

    // Corner of shame
    @Override
    public void collectByType(Namespace data, Namespace assets) {
    }

    @Override
    public void setParent(FileSystemObject parent) {
    }

    @Override
    public FileSystemObject getParent() {
        return null;
    }

    @Override
    public void exit() {
        Project.getInstance().addNamespace(this);
    }
}
