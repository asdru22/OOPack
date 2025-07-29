import com.asdru.oopack.Namespace;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.Subfolder;

import java.nio.file.Path;

public class MyNamespace extends Namespace {

    public MyNamespace() {
        super("foo");

        var s1 = new Subfolder("s1");
        s1.setContent(new AbstractFile("poop.txt", "blah blah") {
            @Override
            public void build(Path parent) {
                System.out.println(parent);
            }
        });
        this.addSubfolder(s1);
    }
}
