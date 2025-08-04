package com.asdru.oopack;

import com.asdru.oopack.internal.Texture;
import com.asdru.oopack.objects.LootTable;
import com.asdru.oopack.objects.Model;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.Function;

public class MyNamespace extends Namespace {
    public LootTable nested = new LootTable("nested","say hi");

    public MyNamespace(Project project) {
        super(project, "foo");
        var s1 = new Folder(this);
        s1.add(new Function("test","1"),
                new Function("2"),
                new LootTable("%s",3),
                new Function("test2","function %s",s1.add(nested)),
                new Folder(this).add(new Model("This is number %s",5)),
                new Texture("sub/test","icon")
                );

    }
}
