package com.asdru.oopack.internal;


import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;

import java.util.List;
import java.util.function.Predicate;

public interface FileSystemObject extends Buildable {
    Object getContent();

    void collectByType(Namespace data, Namespace assets);

    void setParent(FileSystemObject parent);
    FileSystemObject getParent();

    void setProject(Project project);
    Project getProject();

    static FileSystemObject find(FileSystemObject root, Predicate<FileSystemObject> condition) {
        if (condition.test(root)) return root;

        // polymorphic call to get children of either namespace or folder
        Object content = root.getContent();

        if (content instanceof List<?> children) {
            for (Object child : children) {
                if (child instanceof FileSystemObject fso) {
                    FileSystemObject found = find(fso, condition);
                    if (found != null) System.out.println("Found: " + found);
                    if (found != null) return found;
                }
            }
        }

        return null;
    }


}
