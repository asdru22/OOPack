package oopack.resourcepack.objects;


import oopack.Buildable;
import oopack.json.JsonDict;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;

public class Lang extends HashMap<Locale, JsonDict> implements Buildable {
    private Locale defaultLanguage = Locale.US;

    public HashMap<String, String> getLocale(Locale locale) {
        return this.get(locale);
    }

    public void add(String key, String value) {
        add(defaultLanguage, key, value);
    }

    public void add(Locale locale, String key, String value) {
        this.computeIfAbsent(locale, _ -> new JsonDict()).put(key, value);
    }

    public void setDefaultLanguage(Locale locale) {
        defaultLanguage = locale;
    }


    @Override
    public void build(Path buildPath) {
        this.forEach((locale, translations) -> {
            // Create the path for the language file
            Path langPath = buildPath.resolve("lang")
                    .resolve(String.format("%s.json", locale.toString().toLowerCase()));
            translations.build(langPath);
        });
    }

}
