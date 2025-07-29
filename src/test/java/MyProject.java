import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.Subfolder;

import java.nio.file.Path;

public class MyProject extends Project {
    public MyProject() {
        super("new world","MY ptojhect");
    }

    public static void main(String[] args) {
        MyProject p = new MyProject();
        p.addBuildPath(".minecraft");

        p.addNamespace(new MyNamespace());
        p.addNamespace(new Namespace("bar").addSubfolder(new Subfolder("s2").setContent(new AbstractFile("baz","csaasfas") {
            @Override
            public void build(Path parent) {

            }
        })));

        p.build();

        System.out.println(p);
    }
}
