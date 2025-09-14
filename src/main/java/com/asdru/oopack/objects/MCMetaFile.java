package com.asdru.oopack.objects;

import com.asdru.oopack.Namespace;
import com.asdru.oopack.internal.JsonFileFactory;
import com.google.gson.JsonObject;


public class MCMetaFile extends JsonFile {

    public static final JsonFileFactory<MCMetaFile> f = new JsonFile.Factory<>(MCMetaFile.class);

    protected MCMetaFile(String name, JsonObject content) {
        super(name, content);
    }

    @Override
    public String getExtension() {
        return "mcmeta";
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        assets.add(this);
    }

    @Override
    public String getFolderName() {
        return "";
    }
}
