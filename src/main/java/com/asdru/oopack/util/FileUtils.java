package com.asdru.oopack.util;

import com.asdru.oopack.Project;
import com.asdru.oopack.internal.AbstractFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils {

    private static final Logger LOGGER = Logger.getLogger(FileUtils.class.getName());

    public static Logger getLogger(){
        return LOGGER;
    }

    public static void createFolder(Path path, String name) {
        createFolder(path.resolve(name));
    }

    public static void createFolder(Path path) {
        try {
            Files.createDirectories(path);
            LOGGER.info("Folder created: " + path);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create folder: " + path, e);
            throw new RuntimeException("Could not create folder: " + path, e);
        }
    }

    public static void createFile(AbstractFile<?> file, Path path) {
        try {
            file.writeContent(path);
            LOGGER.info("File written to: " + path);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create file: " + path, e);
            throw new RuntimeException("Could not create file: " + path, e);
        }
    }

    public static void createGenericTextFile(Path path, String content) {
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, content);
            LOGGER.info("Text file written to: " + path);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create text file: " + path, e);
            throw new RuntimeException("Could not create text file: " + path, e);
        }
    }

    public static void createGenericPng(Path path, BufferedImage image) {
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create directories for PNG: " + path, e);
            throw new RuntimeException("Directory creation failed for: " + path, e);
        }

        try {
            if (!ImageIO.write(image, "png", path.toFile())) {
                String msg = "No suitable ImageIO writer found for: " + path;
                LOGGER.severe(msg);
                throw new IOException(msg);
            }
            LOGGER.info("PNG image written to: " + path);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write PNG image to file: " + path, e);
            throw new RuntimeException("Could not write PNG image to: " + path, e);
        }
    }

    public static void deleteAllFilesInDirectory(Project project, Path directory) {
        if (!Files.isDirectory(directory)) {
            String msg = directory + " is not a directory.";
            LOGGER.severe(msg);
            throw new IllegalArgumentException(msg);
        }

        try (var paths = Files.walk(directory)) {
            paths.filter(path -> !path.equals(directory))
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                            LOGGER.info("Deleted: " + path);
                        } catch (IOException e) {
                            LOGGER.log(Level.WARNING, "Failed to delete " + path, e);
                        }
                    });
        } catch (IOException e) {
            String msg = "Failed to delete contents of directory: " + directory;
            LOGGER.log(Level.SEVERE, msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    public static BufferedImage loadTexture(String name) {
        String path = "/textures/" + name + ".png";

        try (var stream = FileUtils.class.getResourceAsStream(path)) {
            if (stream == null) {
                String msg = "Resource not found: " + path;
                LOGGER.severe(msg);
                throw new IllegalArgumentException(msg);
            }

            BufferedImage image = ImageIO.read(stream);
            if (image == null) {
                String msg = "ImageIO failed to decode image: " + path;
                LOGGER.severe(msg);
                throw new IOException(msg);
            }

            LOGGER.info("Image loaded from: " + path);
            return image;

        } catch (IOException e) {
            String msg = "Error loading image from " + path;
            LOGGER.log(Level.SEVERE, msg, e);
            throw new RuntimeException("Failed to load image: " + name, e);
        }
    }
}
