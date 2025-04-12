package oopack.datapack.objects;

import oopack.Loggable;
import oopack.datapack.DataEntries;
import oopack.datapack.DataItem;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Structure extends DataItem implements Loggable {

    public Structure(String name) {
        super(DataEntries.SRUCTURE, name, name);
    }

    public void build(Path buildPath, Path structuresPath) {
        try {
            Files.walkFileTree(structuresPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    Path targetDir = buildPath.resolve(structuresPath.relativize(dir));
                    Files.createDirectories(targetDir);
                    logger().fine("Created directory: " + targetDir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Path targetFile = buildPath.resolve(structuresPath.relativize(file));
                    Files.createDirectories(targetFile.getParent());
                    Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            logger().severe("Error while copying structure files: " + e.getMessage());
        }
    }

}
