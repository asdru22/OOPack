package oopack.resourcepack.objects;

import oopack.resourcepack.AssetEntries;
import oopack.resourcepack.AssetItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextureItem extends AssetItem {

    public TextureItem(String destination, String source) {
        super(AssetEntries.TEXTURES, destination, source);
    }

    @Override
    public void makeFile() {
        try {
            InputStream is = TextureItem.class.getClassLoader()
                    .getResourceAsStream(String.format("%s.png", content));
            if (is == null) {
                throw new IOException("Image not found!");
            }
            BufferedImage image = ImageIO.read(is);

            // Get the destination path from a getter function
            Path destinationPath = this.getFileName();
            Files.createDirectories(destinationPath.getParent());
            ImageIO.write(image, "png", destinationPath.toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
