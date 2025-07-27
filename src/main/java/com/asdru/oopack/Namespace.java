package com.asdru.oopack;


public class Namespace implements Buildable {
    public final String name;
    private Data data;
    private Assets assets;

    public Namespace(String name) {
        this.name = name;
    }

    @Override
    public void build() {

    }
}
