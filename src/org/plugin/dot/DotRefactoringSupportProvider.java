package org.plugin.dot;

import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.plugin.dot.psi.DotId;

/**
 * Class defines the refactoring support features which can be used in dot language files.
 * At the moment the only DotIDs renaming is supported refactoring feature dot language files
 * This can be changed in future.
 */
public class DotRefactoringSupportProvider extends RefactoringSupportProvider {

  /**
   * The method checks whether provided element is to be renamed or not.
   * Only DotId elements are expected to be renamed at the moment.
   * This rule can be extended in future.
   * @param element for refactoring
   * @param context for refactoring
   * @return whether element is to be renamed or not
   */
  @Override
  public boolean isMemberInplaceRenameAvailable(PsiElement element, PsiElement context) {
    return element instanceof DotId;
  }


}