package org.plugin.dot.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import org.plugin.dot.DotIcons;
import org.plugin.dot.psi.DotElementFactory;
import org.plugin.dot.psi.DotId;
import org.plugin.dot.psi.DotTypes;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class DotPsiImplUtil {
  private static PsiElement getNotNullId(DotId element){
    List<PsiElement> ids =  Arrays.asList(element.getHtmlId(), element.getSimpleId(), element.getNumeralId(), element.getQuotedAny());
    for(PsiElement id: ids){
      if(id != null){
        return id;
      }
    }
    throw new IllegalStateException("All internal ids are null");
  }

  public static String getName(DotId element){
    return getNotNullId(element).getText();
  }


  public static PsiElement setName(DotId element, String newName){
    if (element != null) {
      ASTNode id = DotElementFactory.createDotId(element.getProject(), newName).getNode();
      // here it's assumed that there is a parent
      element.getNode().getTreeParent().replaceChild(element.getNode(), id);
      return id.getPsi();
    }
    return element;
  }

  public static PsiElement getNameIdentifier(DotId element) {
    ASTNode keyNode = getNotNullId(element).getNode();
    if (keyNode != null) {
      return keyNode.getPsi();
    } else {
      return null;
    }
  }

  public static ItemPresentation getPresentation(final DotId element) {
    return new ItemPresentation() {
      @Nullable
      @Override
      public String getPresentableText() {
        return element.getText();
      }

      @Nullable
      @Override
      public String getLocationString() {
        PsiFile containingFile = element.getContainingFile();
        return containingFile == null ? null : containingFile.getName();
      }

      @Nullable
      @Override
      public Icon getIcon(boolean unused) {
        return DotIcons.FILE;
      }
    };
  }
}
