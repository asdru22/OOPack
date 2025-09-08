package com.asdru.oopack.internal;

import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;


public class Folder extends AbstractFolder<FileSystemObject> implements ContextItem {

    private Project project;

    public Folder(Namespace parent) {
        this.parent = parent;
        parent.add(this);
    }

    // Use generics to avoid downcasting in return
    public <T extends FileSystemObject> T add(T child) {
        child.setParent(this);

        // Walk up the tree to find the Namespace
        FileSystemObject current = this;
        while (current != null && !(current instanceof Namespace)) {
            current = current.getParent();
        }

        if (current instanceof Namespace ns && child instanceof AbstractFile<?> file) {
            file.setNamespaceId(ns.getName());
            file.setProject(this.getProject());
        }

        children.add(child);
        return child;
    }

    public FileSystemObject[] add(FileSystemObject... children) {
        for (FileSystemObject child : children) {
            add(child);
        }
        return children;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }
}
