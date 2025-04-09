package oopack.resourcepack.objects;


import oopack.json.JsonDict;
import oopack.resourcepack.Assets;

import java.nio.file.Path;

public class Sounds extends JsonDict {

    private final Assets assets;

    public Sounds(Assets assets) {
        this.assets = assets;
    }

    @Override
    public void build(Path buildPath) {
        Path soundsPath = buildPath.resolve("sounds.json");
        makeFile(soundsPath);
    }

    public void add(String key, String json, SoundItem... sounds) {
        this.put(key, json);
        for (SoundItem soundItem : sounds) {
            assets.addSoundItem(soundItem);
        }
    }
}
