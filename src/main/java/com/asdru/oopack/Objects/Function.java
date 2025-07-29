package com.asdru.oopack.Objects;

import com.asdru.oopack.internal.TextFile;

import java.util.UUID;


public class Function extends TextFile {

    public  Function(String name, String content) {
        super(String.format("%s.mcfunction",name), content);
    }

    public Function(String name, String content, Object... args) {
        this(name, String.format(content, args));
    }

    public Function(String content) {
        this(UUID.randomUUID().toString().replace("-", ""),content);
    }

    public Function(String content, Object... args) {
        this(String.format(content, args));
    }
}
