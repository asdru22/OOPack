package oopack.resourcepack;

import oopack.NamespaceHolder;
import oopack.resourcepack.objects.Lang;
import oopack.resourcepack.objects.SoundItem;
import oopack.resourcepack.objects.Sounds;
import oopack.resourcepack.objects.TextureItem;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class Assets extends NamespaceHolder {

    private final Map<AssetEntries, AssetItem> assets = new HashMap<>();

    public Assets(String namespace) {
        super(namespace);
    }

    private final Lang lang = new Lang();
    private final Sounds sounds = new Sounds(this);

    public AssetItem add(AssetEntries type, String name, String... contents) {
        AssetItem assetItem = switch (type) {
            case TEXTURES -> new TextureItem(name + ".png", String.join("/", contents));
            case SOUNDS -> new SoundItem(name + ".ogg", String.join("/", contents));
            default -> new AssetItem(type, name + ".json", String.join("\n", contents));
        };

        assets.put(type, assetItem);
        return assetItem;
    }

    public Lang getLang() {
        return lang;
    }

    public Sounds getSounds() {
        return sounds;
    }

    public void addSoundItem(SoundItem soundItem) {
        assets.put(AssetEntries.SOUNDS, soundItem);
    }

    @Override
    public void build(Path output) {
        Path buildPath = output.resolve(this.getNamespace());
        sounds.build(buildPath);
        lang.build(buildPath);
        assets.forEach((_, assetItem) -> assetItem.build(buildPath));
    }
}
