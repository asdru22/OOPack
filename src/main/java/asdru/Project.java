package asdru;

import asdru.datapack.Datapack;
import asdru.resourcepack.Resourcepack;
import asdru.version.Version;
import asdru.version.VersionInfo;

import java.nio.file.Path;
import java.util.Map;

public class Project {
    private final String name, version, worldName;
    private final String output;
    private Datapack datapack;
    private Resourcepack resourcepack = null;

    public Project(String name, String version, String output, String worldName) {
        this.name = name;
        this.version = version;
        this.output = output;
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
        datapack.build(Path.of(
                output, "saves", worldName, "datapacks", name));

        if (resourcepack != null) {
            resourcepack.setVersion(versionInfo.resourcepackVersion());
            resourcepack.build(Path.of(
                    output, "resourcepacks", name));
        }
    }
}
