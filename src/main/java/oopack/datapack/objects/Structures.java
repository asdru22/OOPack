package oopack.datapack.objects;

import oopack.Buildable;

import java.nio.file.Path;
import java.util.ArrayList;

public class Structures implements Buildable {

    private final ArrayList<Structure> structures = new ArrayList<>();

    public void add(String name) {
        structures.add(new Structure(name));
    }

    @Override
    public void build(Path buildPath) {
        String namespace = buildPath.getFileName().toString();
        Path structuresPath = buildPath.getParent().getParent().getParent().getParent()
                .resolve(String.format("generated/%s/structures", namespace));
        structures.forEach(s -> s.build(buildPath.resolve("structure"),structuresPath));
    }
}
