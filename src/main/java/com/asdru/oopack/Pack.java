package com.asdru.oopack;

import com.asdru.oopack.internal.Buildable;
import com.asdru.oopack.internal.Resource;
import com.asdru.oopack.internal.Versionable;
import com.asdru.oopack.util.FileUtils;
import com.asdru.oopack.util.JsonUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class Pack implements Buildable, Versionable {
    private final Project project;
    private final Map<String, Namespace> namespaces = new HashMap<>();
    private final Resource resource; // assets or data

    public Pack(Project project, Resource resource) {
        this.project = project;
        this.resource = resource;
    }

    protected void addNamespace(Namespace namespace) {
        namespaces.put(namespace.getName(), namespace);
    }

    public Namespace getNamespace(String name) {
        return namespaces.get(name);
    }

    private String makeMcMeta() {
        JsonObject root = new JsonObject(), mcmeta = new JsonObject();
        mcmeta.add("description", project.getDescription());

        // polymorphic call
        int minor = this.getMinor();
        int major = this.getMajor();

        if (minor == -1) {
            mcmeta.addProperty("pack_format", major);
        }
        JsonArray arr = new JsonArray();
        arr.add(major);
        arr.add(minor);
        mcmeta.add("min_format", arr);
        mcmeta.add("max_format", arr);


        root.add("pack", mcmeta);
        return JsonUtils.toString(root);
    }


    @Override
    public void build(Path parent) {
        FileUtils.createGenericTextFile(parent.resolve("pack.mcmeta"), makeMcMeta());
        FileUtils.createGenericPng(parent.resolve("pack.png"), FileUtils.loadTexture(project.getIcon()));

        namespaces.values().forEach(namespace -> handleNamespace(parent, namespace));
    }

    protected void handleNamespace(Path parent, Namespace namespace){
        namespace.build(parent.resolve(resource.toString()));
    }

    @Override
    public String toString() {
        return String.format("%s: [%s]", resource, namespaces);
    }

    protected Project getProject() {
        return project;
    }

}
