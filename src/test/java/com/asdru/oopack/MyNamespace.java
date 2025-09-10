package com.asdru.oopack;

import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.Function;

import java.util.Locale;

public class MyNamespace extends Namespace {
    public Function nested = Function.FACTORY.of("nested", """
            {"loot_table":true}
            """);

    public MyNamespace(Project project) {
        super(project, "foo");
        var s1 = new Folder(this);
        s1.add(Function.FACTORY.of("test", "1"));

        utils().addFunctionToLoadTag(s1.add(Function.FACTORY.of("say load")));
        utils().addFunctionToTickTag(s1.add(Function.FACTORY.of("say tick")));
        addTranslation(Locale.ITALY, "key", "value");
        addTranslation("key", "value");

    }

}
