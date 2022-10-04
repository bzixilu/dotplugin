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

    @Override
    public @NotNull FormattingModel createModel(@NotNull FormattingContext formattingContext) {
        final CodeStyleSettings codeStyleSettings = formattingContext.getCodeStyleSettings();
        return FormattingModelProvider
                .createFormattingModelForPsiFile(formattingContext.getContainingFile(),
                        new DotBlock(formattingContext.getNode(),
                                Wrap.createWrap(WrapType.NONE, false),
                                Alignment.createAlignment(),
                                createSpaceBuilder(codeStyleSettings)),
                        codeStyleSettings);
    }

    private static SpacingBuilder createSpaceBuilder(CodeStyleSettings settings) {
        String langId = DotLanguage.INSTANCE.getID();
        return new SpacingBuilder(settings, DotLanguage.INSTANCE)
                .around(DotTypes.EDGE_RHS)
                .spaceIf(settings.getCommonSettings(langId).SPACE_AROUND_EQUALITY_OPERATORS)
                .around(DotTypes.EDGE_OP)
                .spaceIf(settings.getCommonSettings(langId).SPACE_AROUND_EQUALITY_OPERATORS)
                .before(DotTypes.ATTR_LIST)
                .spaces(1)
                .before(DotTypes.CURLY_BRACHET_LEFT)
                .spaceIf(settings.getCommonSettings(langId).SPACE_BEFORE_CLASS_LBRACE)
                .before(DotTypes.BRACHET_LEFT)
                .spaceIf(settings.getCommonSettings(langId).SPACE_BEFORE_METHOD_LBRACE)
                .after(DotTypes.BRACHET_LEFT)
                .spaceIf(settings.getCommonSettings(langId).SPACE_WITHIN_BRACKETS)
                .before(DotTypes.BRACKET_RIGHT)
                .spaceIf(settings.getCommonSettings(langId).SPACE_WITHIN_BRACKETS)
                .after(DotTypes.EOS)
                .spaceIf(settings.getCommonSettings(langId).SPACE_AFTER_SEMICOLON)
                .before(DotTypes.COMMENT)
                .spaces(1)
                .around(DotTypes.EQUAL)
                .spaceIf(settings.getCommonSettings(langId).SPACE_AROUND_ASSIGNMENT_OPERATORS)
                .before(DotTypes.EOS)
                .spaceIf(settings.getCommonSettings(langId).SPACE_BEFORE_SEMICOLON)
                .around(DotTypes.COLON)
                .spaceIf(settings.getCommonSettings(langId).SPACE_AFTER_COLON);
    }

    @Nullable
    @Override
    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }
}