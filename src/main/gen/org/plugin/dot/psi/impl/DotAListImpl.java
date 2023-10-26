// This is a generated file. Not intended for manual editing.
package org.plugin.dot.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.plugin.dot.psi.DotTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.plugin.dot.psi.*;

public class DotAListImpl extends ASTWrapperPsiElement implements DotAList {

  public DotAListImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DotVisitor visitor) {
    visitor.visitAList(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DotVisitor) accept((DotVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<DotId> getIdList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, DotId.class);
  }

  @Override
  @Nullable
  public DotAList getAList() {
    return findChildByClass(DotAList.class);
  }

  @Override
  @Nullable
  public DotCommentStmt getCommentStmt() {
    return findChildByClass(DotCommentStmt.class);
  }

}
