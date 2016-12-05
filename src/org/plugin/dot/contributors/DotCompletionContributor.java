package org.plugin.dot.contributors;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.DotLanguage;
import org.plugin.dot.psi.DotTypes;

public class DotCompletionContributor extends CompletionContributor {
  public DotCompletionContributor() {
    extend(CompletionType.BASIC,
           PlatformPatterns.psiElement(DotTypes.ID).withLanguage(DotLanguage.INSTANCE),
           new CompletionProvider<CompletionParameters>() {
             public void addCompletions(@NotNull CompletionParameters parameters,
                                        ProcessingContext context,
                                        @NotNull CompletionResultSet resultSet) {
               PsiElement psiElement = parameters.getPosition();
               resultSet.addElement(LookupElementBuilder.create("Hello"));
             }
           }
    );
  }
}
