package org.plugin.dot.filetypes;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugin.dot.DotIcons;
import org.plugin.dot.DotLanguage;

import javax.swing.*;

public class DotFileType extends LanguageFileType {
    public static final DotFileType INSTANCE = new DotFileType();

    private DotFileType() {
        super(DotLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "dot file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "dot language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "dot";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return DotIcons.FILE;
    }
}