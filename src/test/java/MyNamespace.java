import com.asdru.oopack.Namespace;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.internal.TextFile;

import java.nio.file.Path;

public class MyNamespace extends Namespace {

    public MyNamespace() {
        super("foo");

        var s1 = new Folder("s1");
        s1.add(new TextFile("poop.txt", "blah blah") {
            @Override
            public void build(Path parent) {
                System.out.println(parent);
            }
        });
        this.addSubfolder(s1);
    }
}
