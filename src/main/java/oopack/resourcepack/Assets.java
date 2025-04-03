package oopack.resourcepack;

import oopack.NamespaceHolder;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Assets extends NamespaceHolder {

    private final Map<AssetEntries, AssetItem> assets = new HashMap<>();

    public Assets(String namespace) {
        super(namespace);
    }


    public AssetItem add(AssetEntries type, String name, String... contents) {
        AssetItem assetItem = type.equals(AssetEntries.TEXTURES) ?
                new TextureItem(type, name + ".png", String.join("/", contents)) :
                new AssetItem(type, name + ".json", String.join("\n", contents));
        assets.put(type,assetItem);
        return assetItem;
    }

    @Override
    public void build(Path output) {
        assets.forEach((_, assetItem) -> assetItem.build(output.resolve(this.getNamespace())));
    }
}
