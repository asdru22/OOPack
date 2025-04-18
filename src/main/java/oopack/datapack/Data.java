package oopack.datapack;

import oopack.MinecraftNamespace;
import oopack.NamespaceHolder;
import oopack.datapack.objects.FunctionTag;
import oopack.datapack.objects.Structures;
import oopack.json.Json;
import oopack.json.ValuesContainer;

import java.nio.file.Path;
import java.util.List;

public class Data extends NamespaceHolder<DataItem,DataEntries> {

    private final Structures structures;

    public Data(String namespace) {
        super(namespace);
        this.structures = new Structures();
    }

    protected DataItem createAndStore(DataEntries type, String name, String content) {
        String finalName = name + (type.equals(DataEntries.FUNCTION) ? ".mcfunction" : ".json");
        DataItem dataItem = new DataItem(type, finalName, content);
        items.put(name, dataItem);
        return dataItem;
    }

    public void addToFunctionTag(FunctionTag functionTag, DataItem function) {
        Datapack datapack = project.getDatapack();
        List<Data> allData = datapack.getData();
        List<String> namespaces = allData.stream()
                .map(Data::getNamespace)
                .toList();

        String MINECRAFT = "minecraft";
        if (!namespaces.contains(MINECRAFT)) {
            datapack.addNamespace(new MinecraftNamespace());
        }

        Data minecraftData = datapack.getDataByNamespace(MINECRAFT);
        String functionName = String.format("%s:%s", this.getNamespace(),
                function.getName().replaceFirst("\\.mcfunction$", ""));

        String tagPath = String.format("function/%s", functionTag.toString().toLowerCase());

        DataItem tag = minecraftData.get(tagPath);
        if (tag == null) {
            minecraftData.add(DataEntries.TAGS, tagPath, String.format("""
                    {
                        "values": [
                            "%s"
                        ]
                    }""", functionName));
        } else {
            String tagContents = tag.getContent();
            ValuesContainer container = Json.fromJson(tagContents, ValuesContainer.class);
            container.addValue(functionName);

            tag.setContent(Json.toJson(container));
        }
    }

    public Structures getStructures() {
        return structures;
    }


    @Override
    public void build(Path output) {
        Path buildPath = output.resolve(this.getNamespace());
        items.forEach((_, dataItem) -> dataItem.build(buildPath));
        structures.build(buildPath);
    }
}
