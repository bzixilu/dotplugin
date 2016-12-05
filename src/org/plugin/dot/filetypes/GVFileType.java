package org.plugin.dot.filetypes;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugin.dot.DotIcons;
import org.plugin.dot.DotLanguage;

import javax.swing.*;

public class GVFileType extends LanguageFileType {
    public static final GVFileType INSTANCE = new GVFileType();

    private GVFileType() {
        super(DotLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "gv file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "dot language gv file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "gv";
    }



    @Nullable
    @Override
    public Icon getIcon() {
        return DotIcons.FILE;
    }
}