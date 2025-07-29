package com.asdru.oopack;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Project {

    String worldName;
    String projectName;
    Datapack datapack = new Datapack();
    Resourcepack resourcepack = new Resourcepack();
    List<Path> buildPaths = new ArrayList<>();
    List<Namespace> namespaces = new ArrayList<>();


    public Project(String worldName, String projectName) {
        this.worldName = worldName;
        this.projectName = projectName;
    }

    public void addBuildPath(String path) {
        buildPaths.add(Path.of(path));
    }

    public void addNamespace(Namespace namespace) {
        namespaces.add(namespace);
    }

    public void build() {

        datapack.setNamespaces(namespaces);
        resourcepack.setNamespaces(namespaces);

        buildPaths.forEach(path -> {
            datapack.build(path.resolve(String.format("saves/%s/datapack-%s", worldName, projectName)));
            resourcepack.build(path.resolve(String.format("resourcepacks/resourcepack-%s", projectName)));
        });
    }

    @Override
    public String toString() {
        return String.format("World name: %s,\nProject Name: %s\nDatapack:  %s,\nResourcePack: %s",
                worldName, projectName,datapack,resourcepack);
    }

}
