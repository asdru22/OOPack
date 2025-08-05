package com.asdru.oopack.internal;


import com.asdru.oopack.util.FileUtils;

import java.nio.file.Path;

public abstract class TextFile extends AbstractFile<StringBuilder> {

    private Object[] args = {};


    protected TextFile(String name, String content) {
        super(name, new StringBuilder(content));
    }

    protected TextFile(String name, String content, Object... args) {
        this(name, content);
        this.args = args;
    }

    @Override
    public void writeContent(Path path) {
        String formattedContent = getContent().toString().formatted(args);
        FileUtils.createGenericTextFile(path,formattedContent);
    }

    public void append(String content){
        setContent(getContent().append(content));
    }
}

