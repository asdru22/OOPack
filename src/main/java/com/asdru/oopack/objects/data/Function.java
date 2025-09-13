package com.asdru.oopack.objects.data;


import com.asdru.oopack.Context;
import com.asdru.oopack.Namespace;
import com.asdru.oopack.internal.ContextItem;
import com.asdru.oopack.internal.FileFactory;
import com.asdru.oopack.objects.PlainFile;
import com.asdru.oopack.objects.TextFile;


public class Function extends TextFile {

    protected Function(String name, String content) {
        super(name, content);
    }

    public static final FunctionFactory f = new FunctionFactory();

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

    public static class FunctionFactory extends PlainFile.Factory<Function, StringBuilder> {

        protected FunctionFactory() {
            super(Function.class);
        }

        @Override
        protected Function instantiate(String name, Object content) {
            return super.instantiate(Context.getFunctionPath() + name, content);
        }
    }
}
