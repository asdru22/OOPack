package com.asdru.oopack.util;

import com.asdru.oopack.internal.AbstractFile;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IOUtils {

    private static final Logger LOGGER = Logger.getLogger(IOUtils.class.getName());

    public static Logger getLogger() {
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


    public static void deleteAllFilesInDirectory(Path directory) {
        if (!Files.isDirectory(directory)) {
            String msg = directory + " is not a directory.";
            LOGGER.severe(msg);
            throw new IllegalArgumentException(msg);
        }

        try (var paths = Files.walk(directory)) {
            paths.filter(path -> !path.equals(directory))
                    .filter(path -> !Files.isSymbolicLink(path)) // skip symlinks/junctions
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

    public static void createGenericOgg(Path path, byte[] data) {
        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create directories for OGG: " + path, e);
            throw new RuntimeException("Directory creation failed for: " + path, e);
        }

        try {
            Files.write(path, data);
            LOGGER.info("OGG file written to: " + path);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to write OGG file: " + path, e);
            throw new RuntimeException("Could not write OGG file to: " + path, e);
        }
    }


    private static <T> T loadResource(String path, Function<InputStream, T> decoder) {
        try (InputStream stream = IOUtils.class.getResourceAsStream(path)) {
            if (stream == null) {
                String msg = "Resource not found: " + path;
                LOGGER.severe(msg);
                throw new IllegalArgumentException(msg);
            }

            T result = decoder.apply(stream);
            if (result == null) {
                String msg = "Decoder failed for resource: " + path;
                LOGGER.severe(msg);
                throw new IOException(msg);
            }

            LOGGER.info("Resource loaded from: " + path);
            return result;

        } catch (IOException e) {
            String msg = "Error loading resource from " + path;
            LOGGER.log(Level.SEVERE, msg, e);
            throw new RuntimeException("Failed to load resource: " + path, e);
        }
    }


    public static BufferedImage loadTexture(String name) {
        String path = "/textures/" + name + ".png";
        return loadResource(path, stream -> {
            try {
                return ImageIO.read(stream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static byte[] loadOgg(String name) {
        String path = "/sounds/" + name + ".ogg";
        return loadResource(path, stream -> {
            try {
                return stream.readAllBytes();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static JsonObject loadJson(String name) {
        String path = "/json/" + name + ".json";
        return loadResource(path, stream -> {
            try {
                String text = new String(stream.readAllBytes());
                return JsonParser.parseString(text).getAsJsonObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}