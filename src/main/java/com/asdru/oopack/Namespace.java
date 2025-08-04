package com.asdru.oopack;

import com.asdru.oopack.internal.FileSystemObject;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Namespace implements FileSystemObject {
    private final String name;
    private Project project;

    public Namespace(Project project, String name) {
        this.name = name;
        this.project = project;
    }

    private final List<FileSystemObject> children = new ArrayList<>();

    public void add(FileSystemObject fso) {
        children.add(fso);
        fso.setProject(project);
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

    // Corner of shame
    @Override
    public void collectByType(Namespace data, Namespace assets) {}
    @Override
    public void setParent(FileSystemObject parent) {}
    @Override
    public FileSystemObject getParent() {return null;}

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public Project getProject() {
        return this.project;
    }
}
