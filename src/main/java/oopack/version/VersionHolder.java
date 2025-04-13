package oopack.version;

import oopack.Buildable;
import oopack.Loggable;
import oopack.Project;
import oopack.json.Json;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public abstract class VersionHolder implements Buildable, Loggable {
    private int version;
    private String packMCMeta = null;
    protected Project project = null;

    public void setVersion(int version) {
        this.version = version;
    }

    public void setPackMCMeta(String packMCMeta) {
        this.packMCMeta = packMCMeta;
    }

    protected void makePackMCMeta(Path output) {
        if (packMCMeta == null) {
            System.err.println("No pack.mcmeta set.");
            System.exit(1);
        }

        Path mcMetaPath = output.resolve("pack.mcmeta");

        packMCMeta = setVersion(packMCMeta);

        try {
            // Create directories if they don't exist
            Files.createDirectories(mcMetaPath.getParent());

            // Write content to file
            Files.writeString(mcMetaPath, packMCMeta,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger().severe("Error when making pack.mcmeta: " + mcMetaPath + " - " + e.getMessage());

        }

    }

    private String setVersion(String packMCMeta) {
        JsonObject jsonMCMeta = Json.parse(packMCMeta);
        jsonMCMeta.getAsJsonObject("pack").addProperty("pack_format", version);
        return Json.toString(jsonMCMeta);
    }

    public abstract void setProject(Project project);

}
