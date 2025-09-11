package com.asdru.oopack.objects;

public interface FileFactory<F> {
    F of(String name, String content, Object... args);
    F of(String content, Object... args);
}