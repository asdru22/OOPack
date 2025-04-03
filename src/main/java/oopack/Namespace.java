package oopack;

import oopack.datapack.Data;
import oopack.resourcepack.Assets;

public class Namespace {
    private final Assets assets;
    private final Data data;

    public Namespace(String namespace) {
        this.assets = new Assets(namespace);
        this.data = new Data(namespace);
    }

    public Assets getAssets() {
        return assets;
    }

    public Data getData() {
        return data;
    }

}
