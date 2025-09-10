package com.asdru.oopack.internal;

public abstract class PlainFile<C> extends AbstractFile<C> {
    protected Object[] args = {};

    protected PlainFile(String name, C content) {
        super(name, content);
    }

    public static class Factory<F extends PlainFile<C>,C> {
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

        protected F createInstance(String name, String content) {
            try {
                return clazz.getConstructor(String.class, String.class)
                        .newInstance(name, content);
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
