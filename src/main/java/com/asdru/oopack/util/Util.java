package com.asdru.oopack.util;

import com.asdru.oopack.Context;
import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.FileSystemObject;
import com.asdru.oopack.objects.MCMetaFile;
import com.asdru.oopack.objects.PlainFile;
import com.asdru.oopack.objects.SoundFile;
import com.asdru.oopack.objects.assets.Sound;
import com.asdru.oopack.objects.assets.Texture;
import com.asdru.oopack.objects.data.Function;
import com.asdru.oopack.objects.assets.Lang;
import com.asdru.oopack.objects.data.tags.FunctionTag;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public final class Util {

    public static void addFunctionToLoadTag(Function f) {
        addFunctionToTag(f, "load");
    }

    public static void addFunctionToTickTag(Function f) {
        addFunctionToTag(f, "tick");
    }

    private static <T extends AbstractFile<JsonObject>> T getOrCreateJsonFile(
            Namespace namespace,
            Class<T> clazz,
            String name,
            Supplier<T> creator // create the object only if needed
    ) {

        Optional<T> optional = namespace.getContent().stream()
                .map(child -> FileSystemObject.find(child, clazz, file -> file.getName().equals(name)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        // file is either the result of the find method
        // or created with supplier if the search returned a null value
        T file = optional.orElseGet(creator);
        return file;
    }

    private static Function getOrCreateFunction(
            Namespace namespace,
            String name,
            Supplier<Function> creator // create the object only if needed
    ) {
        return namespace.getContent().stream()
                .map(child -> FileSystemObject.find(child,
                        Function.class,
                        file -> file.getName().equals(name)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseGet(creator);
    }

    private static void addFunctionToTag(Function f, String target) {
        if (f.getParent() == null) {
            throw new IllegalStateException(
                    String.format("FileSystemObject %s has no parent assigned", f)
            );
        }

        JsonObject content = getOrCreateJsonFile(
                Project.getInstance().getDefaultNamespace(),
                FunctionTag.class,
                target,
                () -> {
                    Namespace mcns = Project.getInstance().getDefaultNamespace();
                    mcns.enter();
                    FunctionTag ft = FunctionTag.f.ofName(target, """
                                {"values":[]}
                            """);
                    mcns.exit();
                    return ft;
                }
        ).getContent();

        JsonArray valuesArray = content.getAsJsonArray("values");
        valuesArray.add(f.toString());
    }

    public static void addTranslation(Namespace namespace, Locale locale, String key, String value) {
        String formattedLocale = LocaleUtils.formatLocale(locale);
        JsonObject content = getOrCreateJsonFile(namespace,
                Lang.class,
                formattedLocale,
                () -> Lang.f.ofName(formattedLocale, "{}")
        ).getContent();
        content.addProperty(key, value);
    }

    public static void addTranslation(Locale locale, String key, String value) {
        addTranslation(Context.getActiveNamespace(), locale, key, value);
    }

    public static void addTranslation(String key, String value) {
        addTranslation(Locale.US, key, value);
    }

    public static String addSound(Namespace namespace, String key, String category,
                                  String subtitle, String translation, Sound... sounds) {

        if (translation != null) {
            addTranslation(subtitle, translation);
        }

        if (sounds.length == 0) {
            throw new IllegalStateException("Sounds array is empty.");
        }
        String dst = "sounds";
        SoundFile sf = getOrCreateJsonFile(namespace,
                SoundFile.class,
                dst,
                () -> SoundFile.f.ofName(dst, "{}")
        );
        JsonObject content = sf.getContent();
        JsonObject soundEntry = new JsonObject();

        JsonArray soundsArray = new JsonArray();
        for (Sound s : sounds) {
            soundsArray.add(s.toString());
        }
        soundEntry.add("sounds", soundsArray);

        soundEntry.addProperty("subtitle", subtitle);
        soundEntry.addProperty("category", category);

        content.add(key, soundEntry);
        return "%s:%s".formatted(namespace.getName(), key);
    }

    public static String addSound(String key, String category, String subtitle, String translation, Sound... sounds) {
        return addSound(Context.getActiveNamespace(), key, category, subtitle, translation, sounds);
    }

    public static String addSound(String key, String category, String translation, Sound... sounds) {
        return addSound(key, category,
                String.format("subtitle.%s.%s", Context.getActiveNamespace().getName(), key), translation,
                sounds);
    }

    public static String addSound(String key, String translation, Sound... sounds) {
        return addSound(key, "master", translation, sounds);
    }

    public static String addSound(String key, Sound... sounds) {
        return addSound(key, "master", null, sounds);
    }


    private static void addToFunction(String content, String target) {
        String functionName = PlainFile.randomNameRaw();

        Function function = getOrCreateFunction(Context.getActiveNamespace(),
                functionName,
                () -> {
                    Function f = Function.f.ofName(functionName, "");
                    addFunctionToTag(f, target);
                    return f;
                }
        );
        function.append(content);
    }

    public static void setOnTick(String content) {
        addToFunction(content, "tick");
    }

    public static void setOnLoad(String content) {
        addToFunction(content, "load");
    }

    public static void setOnLoad(Function function) {
        setOnLoad("function %s".formatted(function));
    }

    public static void setOnTick(Function function) {
        setOnTick("function %s".formatted(function));
    }

    public static String addTooltip(String name,
                                    String backgroundPath, String backgroundJson,
                                    String framePath, String frameJson) {
        String dst = "gui/sprites/tooltip/";

        Texture.of(backgroundPath, "%s%s_background".formatted(dst, name));
        Texture.of(framePath, "%s%s_frame".formatted(dst, name));
        MCMetaFile.f.ofName("textures/%s%s_background.png".formatted(dst, name), backgroundJson);
        MCMetaFile.f.ofName("textures/%s%s_frame.png".formatted(dst, name), frameJson);

        return "%s:%s".formatted(Context.getActiveNamespace().getName(), name);
    }

}
