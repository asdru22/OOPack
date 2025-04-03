package asdru.datapack;

import asdru.NamespaceHolder;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Data extends NamespaceHolder {

    private final Map<DataEntries, DataItem> data = new HashMap<>();

    public Data(String namespace) {
        super(namespace);
    }

    public DataItem add(DataEntries type, String name, String... contents) {
        name += type.equals(DataEntries.FUNCTION) ? ".mcfunction" : ".json";
        DataItem dataItem = new DataItem(type, name, String.join("\n", contents));
        data.put(type, dataItem);
        return dataItem;
    }

    @Override
    public void build(Path output) {
        data.forEach((_, dataItem) -> dataItem.build(output.resolve(this.getNamespace())));
    }
}
