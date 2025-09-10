package com.asdru.oopack.internal;


import com.asdru.oopack.util.FileUtils;

import java.nio.file.Path;

public abstract class TextFile extends PlainFile<StringBuilder> {

    protected TextFile(String name, String content) {
        super(name, new StringBuilder(content));
    }

    public static final Factory<TextFile, StringBuilder> FACTORY = new Factory<>(TextFile.class);


    @Override
    public void writeContent(Path path) {
        String formattedContent = getContent().toString().formatted(args);
        FileUtils.createGenericTextFile(path,formattedContent);
    }

    public void append(String content){
        setContent(getContent().append(content));
    }
}

