package com.asdru.oopack;

import com.asdru.oopack.fileSystem.Buildable;

public class Project implements Buildable {
    private String name;
    private Datapack datapack;
    private Resourcepack resourcepack;
    private String mcMeta;

    public Project() {
        this.name = "my proj";
        this.datapack = new Datapack(this);
        this.resourcepack = new Resourcepack(this);
    }

    public void addNamespace(Namespace namespace) {
        this.datapack.add(namespace.getData());
        this.resourcepack.add(namespace.getAssets());

    }

    protected String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "Datapack: " + this.datapack +"\nResourcepack: " + this.resourcepack;
    }
}
