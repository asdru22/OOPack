package com.asdru.oopack;


import com.asdru.oopack.version.Version;

public class MyProject extends Project {
    public MyProject() {
        super("new world","MY project", Version.LATEST);
    }

    public static void main(String[] args) {
        MyProject p = new MyProject();
        p.addBuildPath("_build");

        MyNamespace ns = new MyNamespace(p);

        p.addNamespace(ns);
        p.setIcon("icon");
        p.setDescription("test project");
        p.disableLogger();
        p.buildZip();
    }
}
