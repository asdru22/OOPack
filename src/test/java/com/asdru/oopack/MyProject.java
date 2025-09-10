package com.asdru.oopack;


import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.Function;
import com.asdru.oopack.objects.PlainFile;
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
        Project.disableLogger();

        Namespace.of("gg");
            Folder.of();
                Function.FACTORY.of("test", "function %s", Function.FACTORY.of("say hji"));
        Context.exit();
        p.build(true);
    }
}
