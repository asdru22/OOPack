package com.asdru.oopack;

public class MyProject extends Project {
    public MyProject() {
        super("new world","MY project");
    }

    public static void main(String[] args) {
        MyProject p = new MyProject();
        p.addBuildPath("_build");

        p.addNamespace(new MyNamespace());
        p.setIcon("icon");
        p.setMcMeta("poop fart");
        p.disableLogger();
        p.build(true);
    }
}
