package com.asdru.oopack.internal;

import java.nio.file.Path;

public interface FileSystemObject extends Buildable {
    Path getPath();
}
