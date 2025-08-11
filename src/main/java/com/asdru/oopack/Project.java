package com.asdru.oopack;

import com.asdru.oopack.objects.MinecraftNamespace;
import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.util.ProjectUtils;

import com.google.gson.JsonObject;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Project {
    private final String worldName;
    private final String projectName;
    private final Version version;
    private final Datapack datapack;
    private final List<Path> buildPaths = new ArrayList<>();

    private final MinecraftNamespace defaultNamespace;
    public final ProjectUtils utils ;

    private Resourcepack resourcepack = null;
    private JsonObject description;
    private String icon;

    public Project(String worldName, String projectName, Version version) {
        this.worldName = worldName;
        this.projectName = projectName;
        this.version = version;
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

    public void setDescription(JsonObject description) {
        this.description = description;
    }

    public void setDescription(String description) {
        JsonObject obj = new JsonObject();
        obj.addProperty("text", description);
        this.description = obj;
    }

    public JsonObject getDescription() {
        return description;
    }

    public void build(boolean clear) {

        this.addNamespace(defaultNamespace);

        if (clear) {
            buildPaths.forEach(FileUtils::deleteAllFilesInDirectory);
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

    public Version getVersion(){
        return version;
    }

    public void buildZip() {

        this.addNamespace(defaultNamespace);

        // Datapack zip in root
        Path datapackZipPath = Path.of(String.format("datapack-%s.zip", projectName));
        try (ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(Files.newOutputStream(datapackZipPath))) {
            Path tempDatapackDir = Files.createTempDirectory("datapack-temp");
            datapack.build(tempDatapackDir);
            zipDirectory(tempDatapackDir, zipOut, tempDatapackDir);
            FileUtils.deleteAllFilesInDirectory(tempDatapackDir);
        } catch (IOException e) {
            throw new RuntimeException("Error creating datapack zip", e);
        }

        // Optional resourcepack zip in root
        if (resourcepack != null) {
            Path resourcepackZipPath = Path.of(String.format("resourcepack-%s.zip", projectName));
            try (ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(Files.newOutputStream(resourcepackZipPath))) {
                Path tempResourceDir = Files.createTempDirectory("resourcepack-temp");
                resourcepack.build(tempResourceDir);
                zipDirectory(tempResourceDir, zipOut, tempResourceDir);
                FileUtils.deleteAllFilesInDirectory(tempResourceDir);
            } catch (IOException e) {
                throw new RuntimeException("Error creating resourcepack zip", e);
            }
        }
    }

    private void zipDirectory(Path folder, ZipArchiveOutputStream zipOut, Path basePath) {
        try (Stream<Path> paths = Files.walk(folder)) {
            paths.filter(Files::isRegularFile)
                    .forEach(file -> {
                        Path relativePath = basePath.relativize(file);
                        try (InputStream in = Files.newInputStream(file)) {
                            ZipArchiveEntry entry = new ZipArchiveEntry(file.toFile(), relativePath.toString().replace("\\", "/"));
                            zipOut.putArchiveEntry(entry);
                            IOUtils.copy(in, zipOut);
                            zipOut.closeArchiveEntry();
                        } catch (IOException e) {
                            throw new RuntimeException("Error zipping file: " + file, e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
