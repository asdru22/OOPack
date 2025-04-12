package oopack.resourcepack.objects;

import oopack.Loggable;
import oopack.resourcepack.AssetEntries;
import oopack.resourcepack.AssetItem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextureItem extends AssetItem implements Loggable {

    private String mcMeta = null;
    private Integer[] selection = null;

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

            // If a selection is defined, crop the image
            if (selection != null && selection.length == 4) {
                int startX = selection[0];
                int startY = selection[1];
                int endX = selection[2];
                int endY = selection[3];
                int width = endX - startX;
                int height = endY - startY;

                // Sanity check
                if (width <= 0 || height <= 0 || startX < 0 || startY < 0 ||
                        endX > image.getWidth() || endY > image.getHeight()) {
                    throw new IllegalArgumentException("Invalid selection bounds");
                }

                image = image.getSubimage(startX, startY, width, height);
            }

            Path destinationPath = this.getFileName();
            if (mcMeta != null) {
                makeMcMeta(destinationPath.toString(), mcMeta);
            }

            Files.createDirectories(destinationPath.getParent());
            ImageIO.write(image, "png", destinationPath.toFile());

        } catch (IOException | IllegalArgumentException e) {
            logger().severe("Error when generating image: " + this.getFileName() + " - " + e.getMessage());

        }
    }


    private void makeMcMeta(String path, String content) {
        Path mcMetaPath = Paths.get(String.format("%s.mcmeta", path));
        System.out.println(mcMetaPath);
        try {
            // Write content to file
            Files.writeString(mcMetaPath, content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void setSelection(int startX, int StartY, int endX, int endY) {
        selection = new Integer[]{startX, StartY, endX, endY};
    }

    public void setMcMeta(String mcMeta) {
        this.mcMeta = mcMeta;
    }
}
