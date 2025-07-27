package com.asdru.oopack;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Project implements Buildable {

    private static Project instance;

    private final Context context;
    private final List<Path> buildDirectories;
    private final String name, version, worldName;
    private final List<Namespace> namespaces;

    protected Project(Builder builder) {
        this.name = builder.name;
        this.version = builder.version;
        this.buildDirectories = new ArrayList<>(builder.buildDirectories);
        this.context = new Context(this.buildDirectories.getFirst());
        this.worldName = builder.worldName;
        this.namespaces =  new ArrayList<>(builder.namespaces);
    }

    public static Project getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Project has not been built yet.");
        }
        return instance;
    }

    public static class Builder {
        private final String name;
        private final String version;
        private final List<Path> buildDirectories = new ArrayList<>();
        private String worldName = null;
        private final List<Namespace> namespaces = new ArrayList<>();

        public Builder(String name, String version) {
            this.name = name;
            this.version = version;
        }

        public Builder addBuildDirectory(String dir) {
            this.buildDirectories.add(Path.of(dir));
            return this;
        }

        public Builder setWorldName(String worldName) {
            this.worldName = worldName;
            return this;
        }

        public Builder addNamespace(Namespace namespace) {
            this.namespaces.add(namespace);
            return this;
        }

        public Project build() {
            if (buildDirectories.isEmpty()) {
                throw new IllegalStateException("At least one build directory must be added.");
            }
            if(name==null) {
                throw new IllegalStateException("World name must be set.");
            }

            if(namespaces.isEmpty()) {
                throw new IllegalStateException("At least one namespace must be added.");
            }

            if (instance != null) {
                throw new IllegalStateException("Project has already been built.");
            }

            instance = new Project(this);
            return instance;
        }
    }

    Context getContext() {
        return context;
    }

    @Override
    public void build() {

    }
}
