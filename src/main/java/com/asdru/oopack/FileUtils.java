package com.asdru.oopack;


import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.Loggable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {
    public static void createFolder(Loggable folder, Path path, String name) {
        FileUtils.createFolder(folder, path.resolve(name));
    }

    public static void createFolder(Loggable folder, Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            folder.logger().log(Level.SEVERE, "Failed to create folder " + path, e);
        }
    }

    public static void createFile(AbstractFile<?> file, Path path) {
        try {
            file.writeContent(path);

        } catch (IOException e) {
            file.logger().log(Level.SEVERE, "Failed to create file: ", e);
        }
    }


    public static void deleteAllFilesInDirectory(Path directory) throws IOException {
        if (Files.isDirectory(directory)) {
            try (var paths = Files.walk(directory)) {
                paths.filter(path -> !path.equals(directory))          // skip the root directory itself
                        .sorted(Comparator.reverseOrder())                 // delete children before parents
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                System.err.println("Failed to delete " + path + ": " + e.getMessage());
                            }
                        });
            }
            System.out.println("Deleted all contents in directory: " + directory);
        } else {
            throw new IllegalArgumentException(directory + " is not a directory");
        }
    }

}

