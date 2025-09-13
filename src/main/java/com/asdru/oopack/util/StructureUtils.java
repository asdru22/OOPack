package com.asdru.oopack.util;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Logger;

public class StructureUtils {

    public static void main(String[] args) throws IOException {
        StructureUtils.importStructures("C:\\Users\\Ale\\AppData\\Roaming\\.minecraft\\saves\\asdrulet 4c");
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    private static final Logger LOGGER = Logger.getLogger(StructureUtils.class.getName());
    private static final Path RESOURCES_BASE = Path.of("src", "main", "resources", "_generated", "structures");

    public static void importStructures(String worldPath) throws IOException {
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
        }
    }

    private static void handleNamespaceDir(Path namespaceDir) throws IOException {
        Path structuresDir = namespaceDir.resolve("structures");
        if (Files.exists(structuresDir)) {
            copyStructures(namespaceDir, structuresDir);
        }
    }


    // copy .nbt files from structuresDir to the target namespace directory
    private static void copyStructures(Path namespaceDir, Path structuresDir) throws IOException {
        Path targetNamespaceDir = RESOURCES_BASE.resolve(namespaceDir.getFileName().toString());
        Files.createDirectories(targetNamespaceDir);

        try (var files = Files.walk(structuresDir)) {
            files.filter(Files::isRegularFile)
                    .filter(f -> f.toString().endsWith(".nbt"))
                    .forEach(src -> copyFile(src, structuresDir, targetNamespaceDir));
        }
    }

    // copy a single file
    private static void copyFile(Path src, Path structuresDir, Path targetNamespaceDir) {
        Path relative = structuresDir.relativize(src);
        Path dst = targetNamespaceDir.resolve(relative);

        try {
            Files.createDirectories(dst.getParent());
            Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
            LOGGER.info("Copied structure: " + src + " -> " + dst);
        } catch (IOException e) {
            LOGGER.severe("Failed to copy structure: " + src + " -> " + e.getMessage());
        }
    }
}
