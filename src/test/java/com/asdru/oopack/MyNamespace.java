package com.asdru.oopack;

import com.asdru.oopack.internal.Texture;
import com.asdru.oopack.objects.LootTable;
import com.asdru.oopack.objects.Model;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.Function;

import java.util.Locale;

public class MyNamespace extends Namespace {
    public Function nested = new Function("nested", """
            {"loot_table":true}
            """);

    public MyNamespace(Project project) {
        super(project, "foo");
        var s1 = new Folder(this);
        s1.add(new Function("test","1"),
                new Function("2"),
                new LootTable("""
                    {"amount" : "%s"}
                    """,3
                ),
                new Function("test2","function %s",s1.add(nested)),
                new Folder(this).add(new Model("""
                        {"model": %s}
                        """,5)),
                new Texture("sub/test","icon")
                );
        
        utils().addFunctionToLoadTag(s1.add(new Function("say load")));
        utils().addFunctionToTickTag(s1.add(new Function("say tick")));
        addTranslation(Locale.ITALY,"key","value");
        addTranslation("key","value");

    }

}
