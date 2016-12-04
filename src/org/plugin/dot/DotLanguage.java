package org.plugin.dot;

import com.intellij.lang.Language;

public class DotLanguage extends Language {
    public static final DotLanguage INSTANCE = new DotLanguage();

    private DotLanguage() {
        super("Dot");
    }
}