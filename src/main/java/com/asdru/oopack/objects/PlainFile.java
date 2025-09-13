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
        public F ofName(String name, String content, Object... args) {
            if (name != null && name.contains(":")) {
                name = name.substring(name.indexOf(":") + 1);
            }
            String formatted = formatContent(content, name, args);
            return createInstance(name, formatted);
        }

        @Override
        public F of(String content, Object... args) {
            String name = randomNameRaw();
            String formatted = formatContent(content, name, args);
            return createInstance(name, formatted);
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


        protected String formatContent(String content, String name, Object... args) {
            // first replace a placeholder for the name
            String withName = content.replace("$name$", name);
            String withNamespace = withName.replace("$ns$", Context.getActiveNamespace().getName());
            // then do normal formatting
            return args.length > 0 ? withNamespace.formatted(args) : withNamespace;
        }
    }

    public static String randomNameRaw() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

    public static String randomName() {
        return String.format("%s:%s",
                Context.getActiveNamespace().getName(),
                java.util.UUID.randomUUID().toString().replace("-", ""));
    }

}
