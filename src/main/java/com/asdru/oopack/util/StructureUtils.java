package com.asdru.oopack.util;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Logger;

public class StructureUtils {

    public static void main(String[] args) {
        StructureUtils.importStructures("C:\\Users\\Ale\\AppData\\Roaming\\.minecraft\\saves\\asdrulet 4c");
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    private static final Logger LOGGER = Logger.getLogger(StructureUtils.class.getName());
    private static final Path RESOURCES_BASE = Path.of("src", "main", "resources", "_generated", "structures");

    public static void importStructures(String worldPath){
        Path generatedRoot = Path.of(worldPath).resolve("generated");

        if (!Files.exists(generatedRoot)) {
            LOGGER.warning("No generated structures found in: " + generatedRoot);
            return;
        }

        // iterate over namespaces in /_generated
        try (var namespaces = Files.list(generatedRoot)) {
            for (Path namespaceDir : namespaces.toList()) {
                if (Files.isDirectory(namespaceDir)) {
                    handleNamespaceDir(namespaceDir);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleNamespaceDir(Path namespaceDir) {
        Path structuresDir = namespaceDir.resolve("structures");
        if (Files.exists(structuresDir)) {
            File targetDir = RESOURCES_BASE.resolve(namespaceDir.getFileName().toString()).toFile();
            try {
                FileUtils.copyDirectory(structuresDir.toFile(), targetDir, true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
