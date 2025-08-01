import com.asdru.oopack.Project;

public class MyProject extends Project {
    public MyProject() {
        super("new world","MY project");
    }

    public static void main(String[] args) {
        MyProject p = new MyProject();
        p.addBuildPath(".minecraft");

        p.addNamespace(new MyNamespace());

        p.build();


    }
}
