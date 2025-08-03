package com.asdru.oopack.internal;

import com.asdru.oopack.FileUtils;
import com.asdru.oopack.Namespace;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class Texture extends AbstractFile<BufferedImage> {

    public Texture(String name, String path) {
        super(name, FileUtils.loadTexture(path));
    }

    @Override
    public void writeContent(Path path)  {
        FileUtils.createGenericPng(path,getContent());
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

