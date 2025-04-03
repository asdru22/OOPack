package asdru.datapack;

import asdru.Namespace;
import asdru.version.VersionHolder;

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
}
