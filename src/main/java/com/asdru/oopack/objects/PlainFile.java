package com.asdru.oopack.objects;

import com.asdru.oopack.Context;
import com.asdru.oopack.Project;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.Folder;

public abstract class PlainFile<C> extends AbstractFile<C> {
    protected Object[] args = {};

    protected PlainFile(String name, C content) {
        super(name, content);
    }

    public static class Factory<F extends PlainFile<C>, C> {
        protected final Class<F> clazz;

        public Factory(Class<F> clazz) {
            this.clazz = clazz;
        }

        // name + content
        public F of(String name, String content) {
            return createInstance(name, content);
        }

        // random name + content
        public F of(String content) {
            return createInstance(randomName(), content);
        }

        // name + content + args
        public F of(String name, String content, Object... args) {
            return createInstance(name, formatContent(content, args));
        }

        // random name + formatted content
        public F of(String content, Object... args) {
            return createInstance(randomName(), formatContent(content, args));
        }


        protected F instantiate(String name, Object content) {
            try {
                Class<?> contentClass = content.getClass();
                Context ctx = Project.getInstance().getContext();

                F instance = clazz.getDeclaredConstructor(String.class, contentClass)
                        .newInstance(name, content);
                ctx.peek().add(instance);
                return instance;
            } catch (Exception e) {
                throw new RuntimeException("Failed to create instance of " + clazz.getSimpleName(), e);
            }
        }


        protected F createInstance(String name, String content) {
            try {
                return instantiate(name, content);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create PlainFile instance", e);
            }
        }

        protected String randomName() {
            return java.util.UUID.randomUUID().toString().replace("-", "");
        }

        protected String formatContent(String content, Object... args) {
            return args.length > 0 ? content.formatted(args) : content;
        }
    }
}
