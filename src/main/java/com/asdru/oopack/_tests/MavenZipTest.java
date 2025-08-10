package com.asdru.oopack._tests;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class MavenZipTest {
    public static void main(String[] args) throws IOException {
        Path outputDir = Paths.get("output");
        Files.createDirectories(outputDir);


        Path file = outputDir.resolve("hello.txt");
        Files.writeString(file, "Hello from GitHub Actions!");

        // zip
        Path zipFile = Paths.get("output.zip");
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(zipFile))) {
            try (InputStream in = Files.newInputStream(file)) {
                ZipEntry entry = new ZipEntry("hello.txt");
                zipOut.putNextEntry(entry);
                in.transferTo(zipOut);
                zipOut.closeEntry();
            }
        }

        System.out.println("Done creating output.zip");
    }
}
