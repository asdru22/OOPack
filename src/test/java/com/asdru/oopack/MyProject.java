package com.asdru.oopack;


import com.asdru.oopack.version.Version;

public class MyProject {

    public static void main(String[] args) {
        Project p = Project.builder().
                worldName("new world").
                projectName("MY project").
                version(Version.LATEST).
                addBuildPath("_build").
                icon("icon").
                description("test project").build();

        p.addNamespace(new MyNamespace(p));

        Project.disableLogger();
        p.buildZip();
    }
}
