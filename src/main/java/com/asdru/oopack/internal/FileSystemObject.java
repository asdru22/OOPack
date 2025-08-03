package com.asdru.oopack.internal;


import com.asdru.oopack.Namespace;

public interface FileSystemObject extends Buildable {
    Object getContent();

    void collectByType(Namespace data, Namespace assets);
}
