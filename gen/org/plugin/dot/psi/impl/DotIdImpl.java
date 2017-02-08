// This is a generated file. Not intended for manual editing.
package org.plugin.dot.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.plugin.dot.psi.DotTypes.*;
import org.plugin.dot.psi.*;
import com.intellij.navigation.ItemPresentation;

public class DotIdImpl extends DotNamedElementImpl implements DotId {

  public DotIdImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DotVisitor visitor) {
    visitor.visitId(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DotVisitor) accept((DotVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getHtmlId() {
    return findChildByType(HTML_ID);
  }

  @Override
  @Nullable
  public PsiElement getNumeralId() {
    return findChildByType(NUMERAL_ID);
  }

  @Override
  @Nullable
  public PsiElement getQuotedAny() {
    return findChildByType(QUOTED_ANY);
  }

  @Override
  @Nullable
  public PsiElement getSimpleId() {
    return findChildByType(SIMPLE_ID);
  }

  public String getName() {
    return DotPsiImplUtil.getName(this);
  }

  public PsiElement setName(String newName) {
    return DotPsiImplUtil.setName(this, newName);
  }

  public PsiElement getNameIdentifier() {
    return DotPsiImplUtil.getNameIdentifier(this);
  }

  public ItemPresentation getPresentation() {
    return DotPsiImplUtil.getPresentation(this);
  }

}
