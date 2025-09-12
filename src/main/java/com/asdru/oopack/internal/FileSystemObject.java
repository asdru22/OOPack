package com.asdru.oopack.internal;


import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface FileSystemObject extends Buildable {
    Object getContent();

    void collectByType(Namespace data, Namespace assets);

    ContextItem getParent();

    String getName();

    static <T extends FileSystemObject> Optional<T> find(
            FileSystemObject root,
            Class<T> clazz,
            Predicate<T> condition
    ) {
        if (clazz.isInstance(root)) {
            // cast to searched class to avoid downcasting in return object
            T casted = clazz.cast(root);
            if (condition.test(casted)) {
                return Optional.of(casted);
            }
        }

        Object content = root.getContent();
        if (content instanceof List<?> children) {
            for (Object child : children) {
                Optional<T> found = find((FileSystemObject) child, clazz, condition);
                if (found.isPresent()) {
                        return found;
                }
            }
        }

        return Optional.empty();
    }
}