package com.asdru.oopack.objects;

import com.asdru.oopack.FileUtils;
import com.asdru.oopack.internal.AbstractFile;

import java.nio.file.Path;
import java.util.UUID;


public abstract sealed class JsonFile extends AbstractFile<String> permits DataJson, AssetsJson {

    private Object[] args;

    public JsonFile(String name, String content) {
        super(name, content);
    }

    public JsonFile(String name, String content, Object... args) {
        this(name, content);
        this.args = args;
    }

    public JsonFile(String content) {
        this(UUID.randomUUID().toString().replace("-", ""),content);
    }

    public JsonFile(String content, Object... args) {
        this(content);
        this.args = args;
    }

    @Override
    public void writeContent(Path path)  {
        String jsonContent = getContent();
        FileUtils.createGenericTextFile(path,getContent());
    }

    @Override
    public String getExtension() {
        return "json";
    }
}
