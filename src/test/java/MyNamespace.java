import com.asdru.oopack.Namespace;
import com.asdru.oopack.objects.LootTable;
import com.asdru.oopack.objects.Model;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.Function;

public class MyNamespace extends Namespace {

    public MyNamespace() {
        super("foo");
        var s1 = new Folder(this);
        var nested = new Function("nested","say hi");
        s1.add(new Function("test","1"),
                new Function("2"),
                new LootTable("%s",3),
                new Function("test2","function %s",s1.add(nested)),
                new Folder(this).add(new Model("This is number %s",5))
                );

    }
}
