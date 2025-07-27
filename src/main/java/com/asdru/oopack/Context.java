package com.asdru.oopack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class Context {
    private final Path path;

    protected Context(Path path) {
        this.path = path;
    }

    protected Path getParent(){
        return path.getParent();
    }

    protected Path resolve(String child){
        return path.resolve(child);
    }

    protected void make() {
        try {
            if (Files.exists(path)) {
                System.out.println("Path already exists: " + path);
                return;
            }

            String name = path.getFileName().toString();

            // Heuristic: if name contains a dot (.) assume it's a file
            if (name.contains(".")) {
                // Ensure parent directory exists
                Path parent = path.getParent();
                if (parent != null && !Files.exists(parent)) {
                    Files.createDirectories(parent);
                }
                Files.createFile(path);
                System.out.println("File created at: " + path);
            } else {
                Files.createDirectories(path);
                System.out.println("Directory created at: " + path);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create path: " + path, e);
        }
    }
}
