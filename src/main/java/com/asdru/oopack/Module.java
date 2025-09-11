package com.asdru.oopack;

import com.asdru.oopack.internal.Folder;

public abstract class Module extends Folder {

    protected Module() {
        super(Context.getActiveNamespace()
                .orElseThrow(() -> new IllegalStateException("No active Namespace in context")));
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

    abstract void content();
}
