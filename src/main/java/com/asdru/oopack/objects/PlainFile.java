package com.asdru.oopack.objects;

import com.asdru.oopack.Context;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.FileFactory;

import java.lang.reflect.Constructor;

public abstract class PlainFile<C> extends AbstractFile<C> {
    protected Object[] args = {};

    public PlainFile(String name, C content) {
        super(name, content);
    }

    protected static class Factory<F extends PlainFile<C>, C> implements FileFactory<F> {
        protected final Class<F> clazz;

        public Factory(Class<F> clazz) {
            this.clazz = clazz;
        }

        @Override
        public F of(String name, String content, Object... args) {
            return createInstance(name, formatContent(content, args));
        }

        @Override
        public F of(String content, Object... args) {
            return createInstance(randomName(), formatContent(content, args));
        }

        protected F instantiate(String name, Object content) {
            try {
                Class<?> contentClass = content.getClass();

                Constructor<F> constructor = clazz.getDeclaredConstructor(String.class, contentClass);
                constructor.setAccessible(true);

                F instance = constructor.newInstance(name, content);
                Context.peek().add(instance);
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
