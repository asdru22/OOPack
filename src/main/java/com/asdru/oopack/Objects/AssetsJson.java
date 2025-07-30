package com.asdru.oopack.Objects;

import com.asdru.oopack.Namespace;

sealed public abstract class AssetsJson extends JsonFile permits Model {
    public AssetsJson(String name, String content) {
        super(name, content);
    }

    public AssetsJson(String name, String content, Object... args) {
        super(name, content, args);
    }

    public AssetsJson(String content) {
        super(content);
    }

    public AssetsJson(String content, Object... args) {
        super(content, args);
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        assets.add(this);
    }
}
