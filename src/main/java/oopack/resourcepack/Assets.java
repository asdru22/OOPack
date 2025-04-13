package oopack.resourcepack;

import oopack.NamespaceHolder;
import oopack.resourcepack.objects.Lang;
import oopack.resourcepack.objects.SoundItem;
import oopack.resourcepack.objects.Sounds;
import oopack.resourcepack.objects.TextureItem;

import java.nio.file.Path;

public class Assets extends NamespaceHolder<AssetItem, AssetEntries> {

    public Assets(String namespace) {
        super(namespace);
    }

    private final Lang lang = new Lang();
    private final Sounds sounds = new Sounds(this);

    @Override
    protected AssetItem createAndStore(AssetEntries type, String name, String content) {
        AssetItem assetItem = switch (type) {
            case TEXTURES -> new TextureItem(name + ".png", String.join("/", content));
            case SOUNDS -> new SoundItem(name + ".ogg", String.join("/", content));
            default -> new AssetItem(type, name + ".json", String.join("\n", content));
        };

        items.put(name, assetItem);
        return assetItem;
    }

    public Lang getLang() {
        return lang;
    }

    public Sounds getSounds() {
        return sounds;
    }

    public void addSoundItem(SoundItem soundItem) {
        items.put(soundItem.getName(), soundItem);
    }

    @Override
    public void build(Path output) {
        Path buildPath = output.resolve(this.getNamespace());
        sounds.build(buildPath);
        lang.build(buildPath);
        items.forEach((_, assetItem) -> assetItem.build(buildPath));
    }
}
