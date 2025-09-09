package com.asdru.oopack.util;

import com.asdru.oopack.Namespace;
import com.asdru.oopack.Project;
import com.asdru.oopack.internal.AbstractFile;
import com.asdru.oopack.internal.FileSystemObject;
import com.asdru.oopack.internal.Folder;
import com.asdru.oopack.internal.JsonFileFactory;
import com.asdru.oopack.objects.Function;
import com.asdru.oopack.objects.FunctionTag;
import com.asdru.oopack.objects.Lang;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Locale;
import java.util.Optional;
import java.util.function.Supplier;

public final class ProjectUtils {
    private final Project project;

    public ProjectUtils(Project project) {
        this.project = project;
    }


    public void addFunctionToLoadTag(Function f) {
        addFunctionToTag(f, "load");
    }

    public void addFunctionToTickTag(Function f) {
        addFunctionToTag(f, "tick");
    }

    private <T extends AbstractFile<JsonObject>> JsonObject getOrCreateJsonFile(
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

    private void addFunctionToTag(Function f, String target) {
        if (f.getParent() == null) {
            throw new IllegalStateException(
                    String.format("FileSystemObject %s has no parent assigned", f)
            );
        }

        JsonObject content = getOrCreateJsonFile(
                project.getDefaultNamespace(),
                FunctionTag.class,
                target,
                () -> JsonFileFactory.of(FunctionTag.class, target, """
                            {"values":[]}
                        """)
        );

        JsonArray valuesArray = content.getAsJsonArray("values");
        valuesArray.add(f.toString());
    }

    public void addTranslation(Namespace namespace, Locale locale, String key, String value) {
        String formattedLocale = LocaleUtils.formatLocale(locale);
        JsonObject content = getOrCreateJsonFile(namespace, Lang.class, formattedLocale, () ->
                JsonFileFactory.of(Lang.class, formattedLocale, "{}")
        );
        content.addProperty(key, value);
    }
}
