package org.plugin.dot.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import org.jetbrains.annotations.*;
import org.plugin.dot.DotLanguage;
import org.plugin.dot.psi.DotTypes;

public class DotFormattingModelBuilder implements FormattingModelBuilder {
    @NotNull
    @Override
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        return FormattingModelProvider.createFormattingModelForPsiFile(element.getContainingFile(),
                new DotBlock(element.getNode(),
                        Wrap.createWrap(WrapType.NONE,
                                false),
                        //Alignment.createAlignment(),
                        null,
                        createSpaceBuilder(settings)),
                settings);
    }

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        return new SpacingBuilder(settings, DotLanguage.INSTANCE)
                .around(DotTypes.EDGE_OP)
                .spaceIf(settings.SPACE_AROUND_ASSIGNMENT_OPERATORS);

    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}