package com.asdru.oopack;

import com.asdru.oopack.objects.MinecraftNamespace;
import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.util.ProjectUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project {
    private final String worldName;
    private final String projectName;
    private final Datapack datapack;
    private Resourcepack resourcepack = null;
    private final List<Path> buildPaths = new ArrayList<>();
    private String mcMeta, icon;
    private final MinecraftNamespace defaultNamespace;
    public final ProjectUtils utils ;

    public Project(String worldName, String projectName) {
        this.worldName = worldName;
        this.projectName = projectName;
        this.datapack = new Datapack(this);
        this.defaultNamespace = new MinecraftNamespace(this);
        this.utils = new ProjectUtils(this);
    }

    public void addBuildPath(String path) {
        buildPaths.add(Path.of(path));
    }

    public void addNamespace(Namespace namespace) {
        final Namespace data = new Namespace(this,namespace.getName());
        final Namespace assets = new Namespace(this, namespace.getName());

        namespace.getContent().forEach(fso -> fso.collectByType(data, assets));
        // add separated data and assets to datapack and resourcepack respectively
        datapack.addNamespace(data);

        if (!assets.getContent().isEmpty()) {
            resourcepack = new Resourcepack(this);
            resourcepack.addNamespace(assets);
        }

    }


    public void build(){
        build(false);
    }

    public void setIcon(String path) {
        this.icon = path;
    }

    public String getIcon() {
        return icon;
    }

    public void setMcMeta(String mcMeta) {
        this.mcMeta = mcMeta;
    }

    public String getMcMeta() {
        return mcMeta;
    }

    public void build(boolean clear) {

        this.addNamespace(defaultNamespace);

        if (clear) {
            buildPaths.forEach(path -> FileUtils.deleteAllFilesInDirectory(this, path));
        }

        buildPaths.forEach(path -> {
            datapack.build(path.resolve(String.format("saves/%s/datapack-%s", worldName, projectName)));
            if (resourcepack != null) {
                resourcepack.build(path.resolve(String.format("resourcepacks/resourcepack-%s", projectName)));
            }
        });
    }

    @Override
    public String toString() {
        return String.format("World name: %s,\nProject Name: %s\nDatapack:  %s,\nResourcePack: %s",
                worldName, projectName,datapack,resourcepack);
    }

    public void disableLogger(){
        Logger logger = FileUtils.getLogger();
        logger.setLevel(Level.OFF);
    }

    public MinecraftNamespace getDefaultNamespace() {
        return defaultNamespace;
    }
}
