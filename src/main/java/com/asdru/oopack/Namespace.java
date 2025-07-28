package com.asdru.oopack;

public class Namespace extends Folder {

    public Namespace(String name) {
        super(name);
        Context ctx = Context.getInstance();
    }

    public void add(Folder folder) {
        children.add(folder);
    }

    @Override
    public void build() {

    }
}
