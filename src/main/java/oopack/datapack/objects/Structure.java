package oopack.datapack.objects;

import oopack.datapack.DataEntries;
import oopack.datapack.DataItem;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Structure extends DataItem {

    public Structure(String name) {
        super(DataEntries.STRUCTURE, name, name);
    }

    public void build(Path buildPath, Path structuresPath) {
        try {
            Files.walkFileTree(structuresPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = buildPath.resolve(structuresPath.relativize(dir));
                    Files.createDirectories(targetDir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetFile = buildPath.resolve(structuresPath.relativize(file));
                    Files.createDirectories(targetFile.getParent()); // Ensure parent folder exists
                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
