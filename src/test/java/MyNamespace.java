import com.asdru.oopack.Namespace;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.Objects.Function;

public class MyNamespace extends Namespace {

    public MyNamespace() {
        super("foo");

        var s1 = new Folder("s1");
        s1.add(new Function("test","1"))
                .add(new Function("2"))
                .add(new Function("%s",3))
                .add(new Function("test2","%s",4));

        this.addFolder(s1);
    }
}
