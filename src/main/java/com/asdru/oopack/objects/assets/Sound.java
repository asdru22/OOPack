package com.asdru.oopack.objects.assets;

import com.asdru.oopack.Context;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.Namespace;

import java.nio.file.Path;

public class Sound extends AbstractFile<byte[]> {

    private Sound(String name, String path) {
        super(name, FileUtils.loadOgg(path));
    }

    public static Sound of(String path) {
        return of(path, path);
    }

    public static Sound of(String src, String dst) {
        Sound instance = new Sound(dst, src);
        Context.peek().add(instance);
        return instance;
    }

    @Override
    public void writeContent(Path path) {
        FileUtils.createGenericOgg(path, getContent());
    }

    @Override
    public String getExtension() {
        return "ogg";
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        assets.add(this);
    }

    @Override
    public String getFolderName() {
        return "sounds";
    }
}
