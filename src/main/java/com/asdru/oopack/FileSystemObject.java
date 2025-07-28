package com.asdru.oopack;

import java.nio.file.Path;

public interface FileSystemObject extends Buildable{

    Path getBuildPath();
}
