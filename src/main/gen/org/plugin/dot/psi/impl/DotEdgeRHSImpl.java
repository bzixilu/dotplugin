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

public class DotEdgeRHSImpl extends ASTWrapperPsiElement implements DotEdgeRHS {

  public DotEdgeRHSImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DotVisitor visitor) {
    visitor.visitEdgeRHS(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DotVisitor) accept((DotVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DotEdgeRHS getEdgeRHS() {
    return findChildByClass(DotEdgeRHS.class);
  }

  @Override
  @Nullable
  public DotNodeId getNodeId() {
    return findChildByClass(DotNodeId.class);
  }

  @Override
  @Nullable
  public DotSubGraph getSubGraph() {
    return findChildByClass(DotSubGraph.class);
  }

  @Override
  @NotNull
  public PsiElement getEdgeOp() {
    return findNotNullChildByType(EDGE_OP);
  }

}
