package oopack;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PackItem<T> implements Buildable, Loggable {
    protected final String name;
    protected String content;
    private final T type;
    private Path fileName;

    public PackItem(T type, String name, String content) {
        this.name = name;
        this.content = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s: [\n%s\n]", name, content);
    }

    @Override
    public void build(Path output) {
        this.fileName = output.resolve(type.toString().toLowerCase()).resolve(name);
        makeFile();
    }

    public Path getFileName() {
        return fileName;
    }

    public String getName(){
        return name;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    protected void makeFile() {
        try {
            // Create directories if they don't exist
            Files.createDirectories(fileName.getParent());

            // Write content to file
            Files.writeString(fileName, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger().severe("Error when creating pack item: " + this.getFileName() + " - " + e.getMessage());

        }
    }
}
