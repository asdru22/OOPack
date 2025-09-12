package com.asdru.oopack;

import com.asdru.oopack.objects.assets.Sound;
import com.asdru.oopack.objects.assets.Texture;
import com.asdru.oopack.objects.data.Function;
import com.asdru.oopack.util.Util;

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
        Util.addTranslation("dsaasda", "b");
        Util.addTranslation("another", "a");

        Function.f.of("AAAA", "bob");
        Texture.of("icon", "b");


        Module.register(
                MyModule.class
        );

        Util.addSound("my_sound",
                Sound.of("test","destination/cool"));

        Context.clear();
        p.build(true);
    }
}
