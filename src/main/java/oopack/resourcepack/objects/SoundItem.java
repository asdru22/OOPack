package oopack.resourcepack.objects;

import oopack.resourcepack.AssetEntries;
import oopack.resourcepack.AssetItem;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SoundItem extends AssetItem {

    public SoundItem(String destination, String source) {
        super(AssetEntries.SOUNDS, destination, source);
    }

    @Override
    public void makeFile() {
        try {
            // Load the .ogg file from the resources
            InputStream is = SoundItem.class.getClassLoader()
                    .getResourceAsStream(String.format("%s.ogg", content));
            if (is == null) {
                throw new IOException("Audio file not found!");
            }

            // Get the destination path from a getter function
            Path destinationPath = this.getFileName();
            Files.createDirectories(destinationPath.getParent());

            // Copy the input stream to the destination file
            Files.copy(is, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            is.close();
        } catch (IOException e) {
            logger().severe("Error when generating sound: " + this.getFileName() + " - " + e.getMessage());

        }
    }

}
