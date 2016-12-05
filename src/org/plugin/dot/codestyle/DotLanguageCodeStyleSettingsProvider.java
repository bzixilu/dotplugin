package org.plugin.dot.codestyle;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.DotLanguage;

public class DotLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
    @NotNull
    @Override
    public Language getLanguage() {
        return DotLanguage.INSTANCE;
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType settingsType) {
        if (settingsType == SettingsType.SPACING_SETTINGS) {
            consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
        } else if (settingsType == SettingsType.INDENT_SETTINGS) {
            consumer.showStandardOptions("INDENT_CHILDREN_OF_GRAPH");
        }
    }

    @Override
    public String getCodeSample(@NotNull SettingsType settingsType) {
        return "# You are reading the \".dot or .gv\" entry.\n" +
                "graph graphname {\n" +
                "// This attribute applies to the graph itself\n" +
                "size=\"1,1\";\n" +
                "// The label attribute can be used to change the label of a node\n" +
                "a [label=\"Foo\"];\n" +
                "// Here, the node shape is changed.\n" +
                "b [shape=box];\n" +
                "// These edges both have different line properties\n" +
                "a -- b -- c [color=blue];\n" +
                "b -- d [style=dotted];\n" +
                "// [style=invis] hides a node.\n";
    }
}
