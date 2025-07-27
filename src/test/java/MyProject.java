import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;

public class MyProject extends Project {
    public MyProject() {
        super(new Builder("MyProject", "1.0")
                .addBuildDirectory("build/output")
                .setWorldName("Overworld")
                .addNamespace(new Namespace("test")));
    }

    public static void main(String[] args) {
        new MyProject();
    }
}
