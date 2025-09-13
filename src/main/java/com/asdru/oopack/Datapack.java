package com.asdru.oopack;

import com.asdru.oopack.internal.Resource;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

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

        // destination: <parent>/data/<namespace>/structure
        Path out = parent.resolve("data").resolve(name).resolve("structure");

        // source: src/main/resources/_generated/structures/<namespace>
        Path generatedRoot = Path.of("src", "main", "resources", "_generated", "structures");
        Path source = generatedRoot.resolve(name);

        if (Files.exists(source) && Files.isDirectory(source)) {
            try {
                // ensure destination exists
                Files.createDirectories(out);

                // copy whole tree (subfolders + files), overwrite existing
                FileUtils.copyDirectory(source.toFile(), out.toFile());
            } catch (IOException e) {
                throw new UncheckedIOException("Error copying namespace " + name, e);
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
