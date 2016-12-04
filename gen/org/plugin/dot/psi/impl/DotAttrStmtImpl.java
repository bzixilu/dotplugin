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

public class DotAttrStmtImpl extends ASTWrapperPsiElement implements DotAttrStmt {

  public DotAttrStmtImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull DotVisitor visitor) {
    visitor.visitAttrStmt(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof DotVisitor) accept((DotVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public DotAttrList getAttrList() {
    return findChildByClass(DotAttrList.class);
  }

  @Override
  @Nullable
  public DotCommentStmt getCommentStmt() {
    return findChildByClass(DotCommentStmt.class);
  }

  @Override
  @Nullable
  public PsiElement getEdge_() {
    return findChildByType(EDGE_);
  }

  @Override
  @Nullable
  public PsiElement getGraph_() {
    return findChildByType(GRAPH_);
  }

  @Override
  @Nullable
  public PsiElement getNode_() {
    return findChildByType(NODE_);
  }

}
