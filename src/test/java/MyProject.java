import com.asdru.oopack.Context;
import com.asdru.oopack.Folder;
import com.asdru.oopack.Namespace;

public class MyProject  {


    public static void main(String[] args) {

        Folder data = new Folder("data");
        Context c = new Context(data);

        var foo = new Namespace("foo");
        data.add(foo);

        foo.add(new MyFolder());

        var bar = new Namespace("bar");
        data.add(bar);

        bar.add(new MyFolder());

        System.out.println(data);

    }
}
