package com.asdru.oopack.util;

import java.util.Locale;

public class LocaleUtils {
    public static String formatLocale(Locale locale) {
        return locale.getLanguage().toLowerCase() + "_" + locale.getCountry().toLowerCase();
    }
}
