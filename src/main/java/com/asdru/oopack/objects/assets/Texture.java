package com.asdru.oopack.objects.assets;

import com.asdru.oopack.Context;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.objects.PlainFile;
import com.asdru.oopack.util.IOUtils;
import com.asdru.oopack.Namespace;

import java.awt.image.BufferedImage;
import java.nio.file.Path;


public class Texture extends AbstractFile<BufferedImage> {

    private Texture(String name, String path) {
        super(name, IOUtils.loadTexture(path));
    }


    private Texture(String name, BufferedImage image) {
        super(name, image);
    }

    public static Texture of(String path) {
        return of(path, path);
    }

    public static Texture of(String src, String dst) {
        Texture instance = new Texture(dst, src);
        Context.peek().add(instance);
        return instance;
    }

    public static Texture of(BufferedImage img) {
        return ofName(PlainFile.randomNameRaw(), img);
    }

    public static Texture ofName(String name, BufferedImage img) {
        if (name != null) {
            if (name.contains(":")) {
                name = name.substring(name.indexOf(":") + 1);
            }
            if (name.endsWith("/")) {
                name = name + PlainFile.randomNameRaw();
            }
        }

        Texture instance = new Texture(name, img);
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

