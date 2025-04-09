package oopack;

import oopack.datapack.Datapack;
import oopack.resourcepack.Resourcepack;
import oopack.version.Version;
import oopack.version.VersionInfo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Project {
    private final String name, version, worldName;
    private final List<String> output = new ArrayList<>();
    private Datapack datapack;
    private Resourcepack resourcepack = null;

    public Project(String name, String version, String output, String worldName) {
        this.name = name;
        this.version = version;
        this.output.add(output);
        this.worldName = worldName;
    }

    public void setDatapack(Datapack datapack) {
        this.datapack = datapack;
    }

    public void setResourcepack(Resourcepack resourcepack) {
        this.resourcepack = resourcepack;
    }

    public void build() {
        Version.getDict().ifPresentOrElse(
                versionMap -> {
                    VersionInfo info = getInfo(versionMap, version);

                    if (info != null) {
                        setVersion(info);
                    } else {
                        System.err.println("Version not found: " + version);
                        System.exit(1);
                    }
                },
                () -> {
                    System.err.println("Failed to fetch version data.");
                    System.exit(1);
                }
        );
    }

    private VersionInfo getInfo(Map<String, VersionInfo> versionMap, String version) {
        return version.equals("latest") ?
                versionMap.entrySet().iterator().next().getValue() :
                versionMap.get(version);
    }

    private void setVersion(VersionInfo versionInfo) {
        datapack.setVersion(versionInfo.datapackVersion());
        output.forEach(output -> {
            datapack.build(Path.of(
                    output, "saves", worldName, "datapacks", name));
            if (resourcepack != null) {
                resourcepack.setVersion(versionInfo.resourcepackVersion());
                resourcepack.build(Path.of(
                        output, "resourcepacks", name));
            }
        });
    }

    public void addPath(String path) {
        this.output.add(path);
    }
}
