package com.asdru.oopack;

import com.asdru.oopack.internal.Buildable;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Project implements Buildable {

    String worldName;
    String projectName;
    Datapack datapack = new Datapack();
    Resourcepack resourcepack = new Resourcepack();
    List<Path> buildPaths = new ArrayList<>();


    public Project(String worldName, String projectName) {
        this.worldName = worldName;
        this.projectName = projectName;
    }

    public void addBuildPath(String path) {
        buildPaths.add(Path.of(path));
    }

    public void addNamespace(Namespace namespace) {
        final Namespace data = new Namespace(namespace.getName());
        final Namespace assets = new Namespace(namespace.getName());

        namespace.getContent().forEach(fso -> fso.collectByType(data, assets));
        // add separated data and assets to datapack and resourcepack respectively
        datapack.addNamespace(data);
        resourcepack.addNamespace(assets);
    }

    @Override
    public String toString() {
        return String.format("World name: %s,\nProject Name: %s\nDatapack:  %s,\nResourcePack: %s",
                worldName, projectName,datapack,resourcepack);
    }

    @Override
    public void build(Path parent) {
        buildPaths.forEach(path -> {
            datapack.build(path.resolve(String.format("saves/%s/datapack-%s", worldName, projectName)));
            resourcepack.build(path.resolve(String.format("resourcepacks/resourcepack-%s", projectName)));
        });
    }

    @Override
    public void setParent(Buildable parent) {

    }

    @Override
    public Buildable getParent() {
        return null;
    }
}
