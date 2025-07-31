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


    public void build() {
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
