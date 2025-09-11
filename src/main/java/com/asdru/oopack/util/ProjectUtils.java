package com.asdru.oopack.util;

import com.asdru.oopack.Context;
import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.FileSystemObject;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.objects.data.Function;
import com.asdru.oopack.objects.data.FunctionTag;
import com.asdru.oopack.objects.assets.Lang;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public final class ProjectUtils {

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

        Optional<T> optional = FileSystemObject.find(
                namespace, clazz, file -> file.getName().equals(name)
        );

        // file is either the result of the find method
        // or created with supplier if the search returned a null value
        T file = optional.orElseGet(() -> {
            T newFile = creator.get();
            namespace.add(new Folder(namespace).add(newFile));
            return newFile;
        });

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

    private static void addTranslation(Namespace namespace, Locale locale, String key, String value) {
        String formattedLocale = LocaleUtils.formatLocale(locale);
        JsonObject content = getOrCreateJsonFile(namespace, Lang.class, formattedLocale, () ->
                Lang.f.of(formattedLocale, "{}")
        );
        content.addProperty(key, value);
    }

    public static void addTranslation(Locale locale, String key, String value) {
        Namespace namespace = Context.getActiveNamespace()
                .orElseThrow(() -> new IllegalStateException("No active Namespace in context"));

        JsonObject json = getOrCreateLangFile(namespace, locale);

        json.addProperty(key, value);
    }

    public static void addTranslation(String key, String value) {
        addTranslation(Locale.US, key, value);
    }

    private static JsonObject getOrCreateLangFile(Namespace namespace, Locale locale) {
        String formattedLocale = LocaleUtils.formatLocale(locale);

        return getOrCreateJsonFile(
                namespace,
                Lang.class,
                formattedLocale,
                () -> Lang.f.of(formattedLocale, "{}"));
    }
}
