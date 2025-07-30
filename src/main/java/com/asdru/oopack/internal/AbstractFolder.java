package com.asdru.oopack.internal;

import com.asdru.oopack.Namespace;
import com.asdru.oopack.Objects.AssetsJson;
import com.asdru.oopack.Objects.DataJson;
import com.asdru.oopack.Objects.Function;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFolder <T extends FileSystemObject> implements FileSystemObject {
    protected final String name;
    protected final List<T> content = new ArrayList<>();

    public AbstractFolder(String name) {
        this.name = name;
    }


    @Override
    public void build(Path parent) {
        content.forEach(f -> f.build(parent));
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return String.format("folder=[Name: %s\nContent:  %s]", name, content);
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        for (FileSystemObject fso : content) {
            fso.collectByType(data, assets);  // polymorphic call
        }
    }

}
