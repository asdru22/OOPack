package com.asdru.oopack.objects;


import com.asdru.oopack.Namespace;
import com.asdru.oopack.internal.TextFile;


public class Function extends TextFile {

    protected Function(String name, String content) {
        super(name, content);
    }

    public static final Factory<Function, StringBuilder> FACTORY =
            new TextFile.Factory<>(Function.class);

    @Override
    public String getFolderName() {
        return "function";
    }

    @Override
    public void collectByType(Namespace data, Namespace assets) {
        data.add(this);
    }

    @Override
    public String getExtension() {
        return "mcfunction";
    }
}
