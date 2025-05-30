package oopack.resourcepack;

import oopack.Namespace;
import oopack.Project;
import oopack.version.VersionHolder;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Resourcepack extends VersionHolder {

    private final List<Assets> assets;

    public Resourcepack(Namespace... namespaces) {
        this.assets = Stream.of(namespaces)
                .map(Namespace::getAssets)
                .collect(Collectors.toList());
    }

    @Override
    public void build(Path output) {
        makePackMCMeta(output);
        assets.forEach(a -> a.build(output.resolve("assets")));
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
        assets.forEach(a -> a.setProject(project));
    }

    public void addNamespace(Namespace namespace) {
        this.assets.add(namespace.getAssets());
    }
}