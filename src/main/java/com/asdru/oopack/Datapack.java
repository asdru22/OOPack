package com.asdru.oopack;

import com.asdru.oopack.internal.Resource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Datapack extends Pack {
    public Datapack(Project project) {
        super(project, Resource.DATA);
    }

    @Override
    protected void handleNamespace(Path parent, Namespace namespace) {
        super.handleNamespace(parent, namespace);
        copyStructures(parent,namespace);
    }

    private void copyStructures(Path parent, Namespace namespace) {
        String name = namespace.getName();
        Path out = parent.resolve("data").resolve(name).resolve("structure");

        Path generatedRoot = Path.of("src", "main", "resources", "_generated", "structures");
        Path source = generatedRoot.resolve(name);
        System.out.println(source);
        if (Files.exists(source) && Files.isDirectory(source)) {
            try {
                // create missing folders
                Files.createDirectories(out);

                // recursive copy
                Files.walk(source).forEach(src -> {
                    try {
                        Path dest = out.resolve(source.relativize(src));
                        if (Files.isDirectory(src)) {
                            Files.createDirectories(dest);
                        } else {
                            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e) {
                        throw new UncheckedIOException("Error copying file: " + src, e);
                    }
                });
            } catch (IOException e) {
                throw new UncheckedIOException("Error handling namespace " + name, e);
            }
        }
    }



    @Override
    public int getMajor() {
        return getProject().getVersion().datapackVersion();
    }

    @Override
    public int getMinor() {
        return getProject().getVersion().datapackVersionMinor();
    }
}
