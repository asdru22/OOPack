package com.asdru.oopack.internal;

import com.asdru.oopack.Context;

public interface ContextItem {
    default void enter() {
        Context.enter(this);
    }

    default void exit() {
        Context.exit();
    }
    FileSystemObject add(FileSystemObject fso);

}
