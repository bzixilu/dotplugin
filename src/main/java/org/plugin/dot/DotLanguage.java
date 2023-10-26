package org.plugin.dot;

import com.intellij.lang.Language;

/**
 * Class-holder of dot language instance
 */
public class DotLanguage extends Language {
    public static final DotLanguage INSTANCE = new DotLanguage();

    private DotLanguage() {
        super("Dot");
    }
}