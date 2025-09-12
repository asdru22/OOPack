package com.asdru.oopack.internal;

import com.asdru.oopack.Context;

public interface ContextItem {


    default void enter() {
        Context.getStack().push(this);
    }

    default void exit() {
    }
    FileSystemObject add(FileSystemObject fso);

}
