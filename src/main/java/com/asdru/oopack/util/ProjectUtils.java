package com.asdru.oopack.util;

import com.asdru.oopack.Project;
import com.asdru.oopack.internal.FileSystemObject;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.Function;
import com.asdru.oopack.objects.FunctionTag;
import com.asdru.oopack.objects.MinecraftNamespace;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public final class ProjectUtils {
    private final Project project;

    public ProjectUtils(Project project) {
        this.project = project;
    }

    public void addFunctionToLoadTag(Function f){

        if (f.getParent() == null) {
            throw new IllegalStateException(
                    String.format("FileSystemObject %s has no parent assigned", f)
            );
        }

        MinecraftNamespace minecraft = project.getDefaultNamespace();
        FunctionTag loadTag;
        FileSystemObject res = FileSystemObject.find(minecraft, fso ->
                fso instanceof FunctionTag functionTag && functionTag.getName().equals("load"));

        if(res != null){
            loadTag = (FunctionTag)res;
        } else {
            loadTag = new FunctionTag("load", """
                {"values":[]}
                """);
            minecraft.add(new Folder(minecraft).add(loadTag));
        }

        JsonObject content = loadTag.getContent();
        JsonArray valuesArray = content.getAsJsonArray("values");
        valuesArray.add(f.toString());

    }
}
