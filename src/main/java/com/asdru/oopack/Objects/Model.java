package com.asdru.oopack.Objects;

public non-sealed class Model extends AssetsJson {

    public Model(String name, String content) {
        super(name, content);
    }

    public Model(String name, String content, Object... args) {
        super(name, content, args);
    }

    public Model(String content) {
        super(content);
    }

    public Model(String content, Object... args) {
        super(content, args);
    }
}
