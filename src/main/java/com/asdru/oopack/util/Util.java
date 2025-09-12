package com.asdru.oopack.util;

import com.asdru.oopack.Context;
import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.FileSystemObject;
import com.asdru.oopack.objects.JsonFile;
import com.asdru.oopack.objects.SoundFile;
import com.asdru.oopack.objects.assets.Sound;
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

    private static <T extends AbstractFile<JsonObject>> JsonObject getOrCreateJsonFile(
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
        return file.getContent();
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
                () -> FunctionTag.f.of(target, """
                            {"values":[]}
                        """)
        );

        JsonArray valuesArray = content.getAsJsonArray("values");
        valuesArray.add(f.toString());
    }

    public static void addTranslation(Namespace namespace, Locale locale, String key, String value) {
        String formattedLocale = LocaleUtils.formatLocale(locale);
        JsonObject content = getOrCreateJsonFile(namespace,
                Lang.class,
                formattedLocale,
                () -> Lang.f.of(formattedLocale, "{}")
        );
        content.addProperty(key, value);
    }

    public static void addTranslation(Locale locale, String key, String value) {
        addTranslation(Context.getActiveNamespace(), locale, key, value);
    }

    public static void addTranslation(String key, String value) {
        addTranslation(Locale.US, key, value);
    }

    public static void addSound(Namespace namespace, String key, String category,
                                String subtitle, Sound... sounds) {

        if(sounds.length == 0) {
            throw new IllegalStateException("Sounds array is empty.");
        }

        String dst = "sounds";
        JsonObject content = getOrCreateJsonFile(namespace,
                SoundFile.class,
                dst,
                () -> SoundFile.f.of(dst, "{}")
        );

        JsonObject soundEntry = new JsonObject();

        JsonArray soundsArray = new JsonArray();
        for (Sound s : sounds) {
            soundsArray.add(s.toString());
        }
        soundEntry.add("sounds", soundsArray);

        soundEntry.addProperty("subtitle", subtitle);
        soundEntry.addProperty("category", category);

        content.add(key, soundEntry);
    }

    public static void addSound(String key, String category, String subtitle, Sound... sounds) {
        addSound(Context.getActiveNamespace(), key, category, subtitle, sounds);
    }

    public static void addSound(String key, String category, Sound... sounds) {
        addSound(key, category,
                String.format("subtitle.%s.%s", Context.getActiveNamespace().getName(), key),
                sounds);
    }

    public static void addSound(String key, Sound... sounds) {
        addSound(key, "master", sounds);
    }

}
