package com.asdru.oopack.internal;

import com.asdru.oopack.Project;

public interface ContextItem {

    Project getProject();

    default void enter() {
        getProject().getContext().push(this);
    }

    default void exit() {
    }
    FileSystemObject add(FileSystemObject fso);

}
