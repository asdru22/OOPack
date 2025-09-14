package com.asdru.oopack.objects;


import com.asdru.oopack.util.IOUtils;

import java.nio.file.Path;

public abstract class TextFile extends PlainFile<StringBuilder> {

    protected TextFile(String name, String content) {
        super(name, new StringBuilder(content));
    }

    static final Factory<TextFile, StringBuilder> f = new Factory<>(TextFile.class);


    @Override
    public void writeContent(Path path) {
        String formattedContent = getContent().toString().formatted(args);
        IOUtils.createGenericTextFile(path, formattedContent);
    }

    public void append(String content) {
        setContent(getContent().append(PlainFile.replaceKeywords(content, getName())));
    }
}

