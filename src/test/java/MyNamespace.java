import com.asdru.oopack.Namespace;
import com.asdru.oopack.objects.LootTable;
import com.asdru.oopack.objects.Model;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.Function;

public class MyNamespace extends Namespace {

    public MyNamespace() {
        super("foo");

        var s1 = new Folder("s1");
        s1.add(new Function("test","1"))
                .add(new Function("2"))
                .add(new LootTable("%s",3))
                .add(new Function("test2","%s",4))
                .add(new Folder("subfolder").add(new Model("This is number %s",5)));
        this.add(s1);

        System.out.println(new Function("bobob"));
    }
}
