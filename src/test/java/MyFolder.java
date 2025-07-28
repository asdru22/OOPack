import com.asdru.oopack.Folder;
import com.asdru.oopack.File;


public class MyFolder extends Folder {
    public MyFolder() {
        super("functions");

        new File("bar.mcfunction");
        new File("foo.mcfunction");

    }
}
