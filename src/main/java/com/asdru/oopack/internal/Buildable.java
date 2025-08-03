package com.asdru.oopack.internal;

import java.nio.file.Path;

public interface Buildable extends Loggable {
    void build(Path parent);
}
