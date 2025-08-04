package com.asdru.oopack.internal;


import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;

public interface FileSystemObject extends Buildable {
    Object getContent();

    void collectByType(Namespace data, Namespace assets);

    void setParent(FileSystemObject parent);
    FileSystemObject getParent();

    void setProject(Project project);
    Project getProject();
}
