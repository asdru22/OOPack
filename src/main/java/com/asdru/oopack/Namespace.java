package com.asdru.oopack;

import com.asdru.oopack.datapack.DataItem;
import com.asdru.oopack.fileSystem.Buildable;
import com.asdru.oopack.fileSystem.File;
import com.asdru.oopack.resourcepack.AssetItem;

public class Namespace implements Buildable {
    private String name;
    private Data data = new Data();
    private Assets assets = new Assets();

    public Namespace(String name){
        this.name = name;
    }

    protected Data getData(){
        return data;
    }

    protected Assets getAssets(){
        return assets;
    }

    public void add(File f){
        if(f instanceof DataItem){
            data.add((DataItem)f);
        }
        else if (f instanceof AssetItem){
            assets.add((AssetItem)f);
        }
    }

}
