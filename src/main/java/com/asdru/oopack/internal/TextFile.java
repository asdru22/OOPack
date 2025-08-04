package com.asdru.oopack.internal;


import com.asdru.oopack.util.FileUtils;

import java.nio.file.Path;

public abstract class TextFile extends AbstractFile<String> {

    protected TextFile(String name, String content) {
        super(name, content);
    }

    protected TextFile(String name, String content, Object... args) {
        this(name, String.format(content, args));
    }

    @Override
    public void writeContent(Path path) {
        FileUtils.createGenericTextFile(path,getContent());
    }

    public void append(String content){
        setContent(content + getContent());
    }
}

