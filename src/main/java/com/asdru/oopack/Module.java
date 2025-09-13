package com.asdru.oopack;

import com.asdru.oopack.internal.Folder;

public abstract class Module extends Folder {

    private String functionPath = "";

    protected Module() {
        super(Context.getActiveNamespace());
        this.enter();
        content();
        this.exit();
    }

    @SafeVarargs
    public static void register(Class<? extends Module>... classes) {
        for (Class<? extends Module> clazz : classes) {
            try {
                clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Could not create feature: " + clazz.getSimpleName(), e);
            }
        }
    }

    @Override
    public String toString() {
        return "module(" + getClass().getSimpleName() + ")";
    }

    protected abstract void content();

    protected void setFunctionPath(String path){
        this.functionPath = path;
    }

    public String getFunctionPath(){
        return functionPath;
    }
}
