package com.asdru.oopack;

import com.asdru.oopack.internal.Resource;

public class Datapack extends Pack {
    public Datapack(Project project) {
        super(project, Resource.DATA);
    }


    @Override
    public int getMajor() {
        return getProject().getVersion().getDatapackVersion();
    }

    @Override
    public int getMinor() {
        return getProject().getVersion().getDatapackVersionMinor();
    }
}
