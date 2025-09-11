package com.asdru.oopack;


import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.Function;
import com.asdru.oopack.util.ProjectUtils;
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
        Function.f.of("test", "function %s", Function.f.of("say hji"));
        ProjectUtils.addTranslation("a","b");
        ProjectUtils.addTranslation("b","c");
        ProjectUtils.addTranslation("a","b");

        Context.clear();
        p.build(true);
    }
}
