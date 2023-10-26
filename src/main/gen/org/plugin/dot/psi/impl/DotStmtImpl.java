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

public class DotStmtImpl extends ASTWrapperPsiElement implements DotStmt {

  public DotStmtImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DotVisitor visitor) {
    visitor.visitStmt(this);
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
  public DotAttrStmt getAttrStmt() {
    return findChildByClass(DotAttrStmt.class);
  }

  @Override
  @Nullable
  public DotCommentStmt getCommentStmt() {
    return findChildByClass(DotCommentStmt.class);
  }

  @Override
  @Nullable
  public DotEdgeStmt getEdgeStmt() {
    return findChildByClass(DotEdgeStmt.class);
  }

  @Override
  @Nullable
  public DotNodeStmt getNodeStmt() {
    return findChildByClass(DotNodeStmt.class);
  }

  @Override
  @Nullable
  public DotSubGraph getSubGraph() {
    return findChildByClass(DotSubGraph.class);
  }

}
