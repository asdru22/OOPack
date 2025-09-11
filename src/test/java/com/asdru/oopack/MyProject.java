package com.asdru.oopack;

import com.asdru.oopack.objects.Function;
import com.asdru.oopack.util.ProjectUtils;

public class MyProject {

    public static void main(String[] args) {
        Project p = Project.builder().
                worldName("new world").
                projectName("MY project").
                version("latest").
                addBuildPath("_build").
                icon("icon").
                description("test project").build();
        Project.disableLogger();

        Namespace.of("gg");
        ProjectUtils.addTranslation("dsaasda", "b");
        Function.f.of("AAAA","bob");
        Module.register(
                MyModule.class
        );

        Context.clear();
        p.build(true);
    }
}
