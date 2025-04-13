package oopack.datapack;

import oopack.Namespace;
import oopack.Project;
import oopack.version.VersionHolder;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Datapack extends VersionHolder {

    private final List<Data> data;

    // add data of all namespaces
    public Datapack(Namespace... namespaces) {
        this.data = Stream.of(namespaces)
                .map(Namespace::getData)
                .collect(Collectors.toList());
    }

    @Override
    public void build(Path output) {
        makePackMCMeta(output);
        data.forEach(d -> d.build(output.resolve("data")));
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
        data.forEach(d -> d.setProject(project));
    }

    public List<Data> getData() {
        return data;
    }

    public void addNamespace(Namespace namespace) {
        this.data.add(namespace.getData());
    }

    public Data getDataByNamespace(String namespace) {
        return data.stream()
                .filter(d -> d.getNamespace().equals(namespace))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No data found for namespace: " + namespace));
    }
}
