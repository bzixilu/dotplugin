package org.plugin.dot;

import com.intellij.codeInsight.lookup.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.*;
import org.plugin.dot.psi.DotId;

import java.util.*;

public class DotReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
  private String id;

  public DotReference(@NotNull PsiElement element, TextRange textRange) {
    super(element, textRange);
    id = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
  }

  @NotNull
  @Override
  public ResolveResult[] multiResolve(boolean incompleteCode) {
    Project project = myElement.getProject();
    final Iterable<DotId> ids = DotPSITreeUtil.findDotIds(project, id);
    List<ResolveResult> results = new ArrayList<ResolveResult>();
    for (DotId id : ids) {
      results.add(new PsiElementResolveResult(id));
    }
    return results.toArray(new ResolveResult[results.size()]);
  }

  @Nullable
  @Override
  public PsiElement resolve() {
    ResolveResult[] resolveResults = multiResolve(false);
    return resolveResults.length > 0 ? resolveResults[0].getElement() : null;
  }

  @NotNull
  @Override
  public Object[] getVariants() {
    Project project = myElement.getProject();
    Iterable<DotId> ids = DotPSITreeUtil.findDotIds(project);
    List<LookupElement> variants = new ArrayList<>();
    for (final DotId id : ids) {
      if (id != null) {
        variants.add(LookupElementBuilder.create(id).
            withIcon(DotIcons.FILE).
            withTypeText(id.getContainingFile().getName())
        );
      }
    }
    return variants.toArray();
  }
}