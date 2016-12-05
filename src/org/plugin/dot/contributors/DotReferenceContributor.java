package org.plugin.dot.contributors;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.DotPSITreeUtil;
import org.plugin.dot.psi.DotId;
import org.plugin.dot.DotReference;
import org.plugin.dot.psi.impl.DotIdImpl;

import java.util.ArrayList;
import java.util.List;

public class DotReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        registrar.registerReferenceProvider(StandardPatterns.instanceOf(DotIdImpl.class), new PsiReferenceProvider() {
            @NotNull
            @Override
            public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                if (element instanceof DotId) {
                    List<PsiReference> l = new ArrayList<>();
                    for(PsiElement e: DotPSITreeUtil.findDotIds(element.getProject(), ((DotId) element).getName())){
                        String value = e.getText();
                        if (value != null) {
                            l.add(new DotReference(element, new TextRange(0, value.length() + 1)));
                        }
                    }
                    PsiReference[] arr = new PsiReference[l.size()];
                    return l.toArray(arr);
                }
                return PsiReference.EMPTY_ARRAY;
            }
        });
    }
}
