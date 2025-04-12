package oopack.datapack;

import oopack.NamespaceHolder;
import oopack.datapack.objects.Structures;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Data extends NamespaceHolder {

    private final Map<DataEntries, DataItem> data = new HashMap<>();
    private final Structures structures;

    public Data(String namespace) {
        super(namespace);
        this.structures = new Structures();
    }

    public DataItem add(DataEntries type, String name, String... contents) {
        name += type.equals(DataEntries.FUNCTION) ? ".mcfunction" : ".json";
        DataItem dataItem = new DataItem(type, name, String.join("\n", contents));
        data.put(type, dataItem);
        return dataItem;
    }

    public Structures getStructures() {
        return structures;
    }


    @Override
    public void build(Path output) {
        Path buildPath = output.resolve(this.getNamespace());
        data.forEach((_, dataItem) -> dataItem.build(buildPath));
        structures.build(buildPath);
    }
}
