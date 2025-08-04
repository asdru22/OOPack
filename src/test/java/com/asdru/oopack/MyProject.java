package com.asdru.oopack;

public class MyProject extends Project {
    public MyProject() {
        super("new world","MY project");
    }

    public static void main(String[] args) {
        MyProject p = new MyProject();
        p.addBuildPath("_build");

        MyNamespace ns = new MyNamespace(p);

        p.addNamespace(ns);
        p.setIcon("icon");
        p.setMcMeta("testing");
        p.disableLogger();
        p.build(true);
    }
}
