package org.plugin.dot.contributors;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.DotLanguage;
import org.plugin.dot.psi.DotFile;

import static org.plugin.dot.DotPSITreeUtil.findDotIds;
import static org.plugin.dot.psi.DotTypes.SIMPLE_ID;

public class DotCompletionContributor extends CompletionContributor {
    public DotCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement().withLanguage(DotLanguage.INSTANCE),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        PsiElement psiElement = parameters.getPosition();
                        final IElementType elementType = psiElement.getNode().getElementType();
                        if (elementType.equals(SIMPLE_ID)) {
                            for (String id : findDotIds((DotFile) psiElement.getContainingFile())) {
                                resultSet.addElement(LookupElementBuilder.create(id).withTypeText("ID"));
                            }
                        }
                    }
                }
        );
    }
}
