package com.asdru.oopack.internal;

import com.asdru.oopack.Context;
import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;


public class Folder extends AbstractFolder<FileSystemObject> implements ContextItem {

    public Folder(ContextItem parent) {
        this.parent = parent;
        parent.add(this);
    }

    public static Folder of() {
        return new Folder(Context.peek());
    }

    // Use generics to avoid downcasting in return
    public FileSystemObject add(FileSystemObject child) {
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
    public String getName() {
        return "folder";
    }
}
