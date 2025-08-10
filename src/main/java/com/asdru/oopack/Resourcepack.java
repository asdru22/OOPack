package com.asdru.oopack;

import com.asdru.oopack.internal.Resource;

public class Resourcepack extends Pack {
    public Resourcepack(Project project) {
        super(project, Resource.ASSETS);
    }

    @Override
    public int getMajor() {
        return getProject().getVersion().getResourcepackVersion();
    }

    @Override
    public int getMinor() {
        return getProject().getVersion().getResourcepackVersionMinor();
    }
}
