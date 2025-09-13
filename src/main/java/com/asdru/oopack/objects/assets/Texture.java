package com.asdru.oopack.objects.assets;

import com.asdru.oopack.Context;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.util.IOUtils;
import com.asdru.oopack.Namespace;

import java.awt.image.BufferedImage;
import java.nio.file.Path;


public class Texture extends AbstractFile<BufferedImage> {

    private Texture(String name, String path) {
        super(name, IOUtils.loadTexture(path));
    }

    public static Texture of(String path) {
        return of(path, path);
    }

    public static Texture of(String src, String dst) {
        Texture instance = new Texture(dst, src);
        Context.peek().add(instance);
        return instance;
    }

    @Override
    public void writeContent(Path path) {
        IOUtils.createGenericPng(path, getContent());
    }

    @Override
    public String getExtension() {
        return "png";
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        assets.add(this);
    }

    @Override
    public String getFolderName() {
        return "textures";
    }
}

