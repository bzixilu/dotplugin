// This is a generated file. Not intended for manual editing.
package org.plugin.dot.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import org.plugin.dot.DotNamedElement;

public interface DotId extends DotNamedElement {

  @Nullable
  PsiElement getHtmlId();

  @Nullable
  PsiElement getNumeralId();

  @Nullable
  PsiElement getQuotedAny();

  @Nullable
  PsiElement getSimpleId();

}
