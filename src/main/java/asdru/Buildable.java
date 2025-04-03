package asdru;

import java.nio.file.Path;

public interface Buildable {
    void build(Path buildPath);
}
