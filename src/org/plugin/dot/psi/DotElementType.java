package org.plugin.dot.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.DotLanguage;

public class DotElementType extends IElementType {
    public DotElementType(@NotNull @NonNls String debugName) {
        super(debugName, DotLanguage.INSTANCE);
    }
}