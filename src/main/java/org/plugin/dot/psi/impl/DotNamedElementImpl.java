package org.plugin.dot.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.psi.DotNamedElement;

public abstract class DotNamedElementImpl extends ASTWrapperPsiElement implements DotNamedElement {
  public DotNamedElementImpl(@NotNull ASTNode node) {
    super(node);
  }
}