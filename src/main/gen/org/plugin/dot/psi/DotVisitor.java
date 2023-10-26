// This is a generated file. Not intended for manual editing.
package org.plugin.dot.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class DotVisitor extends PsiElementVisitor {

  public void visitEol(@NotNull DotEol o) {
    visitPsiElement(o);
  }

  public void visitId(@NotNull DotId o) {
    visitNamedElement(o);
  }

  public void visitAList(@NotNull DotAList o) {
    visitPsiElement(o);
  }

  public void visitAttrList(@NotNull DotAttrList o) {
    visitPsiElement(o);
  }

  public void visitAttrStmt(@NotNull DotAttrStmt o) {
    visitPsiElement(o);
  }

  public void visitCharacter(@NotNull DotCharacter o) {
    visitPsiElement(o);
  }

  public void visitCommentStmt(@NotNull DotCommentStmt o) {
    visitPsiElement(o);
  }

  public void visitCompassPt(@NotNull DotCompassPt o) {
    visitPsiElement(o);
  }

  public void visitDotgraphStmt(@NotNull DotDotgraphStmt o) {
    visitPsiElement(o);
  }

  public void visitEdgeRHS(@NotNull DotEdgeRHS o) {
    visitPsiElement(o);
  }

  public void visitEdgeStmt(@NotNull DotEdgeStmt o) {
    visitPsiElement(o);
  }

  public void visitNodeId(@NotNull DotNodeId o) {
    visitPsiElement(o);
  }

  public void visitNodeStmt(@NotNull DotNodeStmt o) {
    visitPsiElement(o);
  }

  public void visitPort(@NotNull DotPort o) {
    visitPsiElement(o);
  }

  public void visitStmt(@NotNull DotStmt o) {
    visitPsiElement(o);
  }

  public void visitStmtList(@NotNull DotStmtList o) {
    visitPsiElement(o);
  }

  public void visitSubGraph(@NotNull DotSubGraph o) {
    visitPsiElement(o);
  }

  public void visitSymbol(@NotNull DotSymbol o) {
    visitPsiElement(o);
  }

  public void visitNamedElement(@NotNull DotNamedElement o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
