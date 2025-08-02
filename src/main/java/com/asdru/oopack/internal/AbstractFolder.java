package com.asdru.oopack.internal;

import com.asdru.oopack.Namespace;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFolder <T extends FileSystemObject> implements FileSystemObject {
    protected final List<T> content = new ArrayList<>();

    public AbstractFolder() {
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
    public String toString() {
        return content.toString();
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        for (FileSystemObject fso : content) {
            fso.collectByType(data, assets);  // polymorphic call
        }
    }

}
