import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;
import com.asdru.oopack.datapack.items.Function;

public class MyProject  {

    public static void main(String[] args) {
        Project project = new Project();
        System.out.println(project);

        Namespace ns = new Namespace("tes");
        ns.add(new Function());

        project.addNamespace(ns);
    }
}
