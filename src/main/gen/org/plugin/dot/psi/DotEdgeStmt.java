// This is a generated file. Not intended for manual editing.
package org.plugin.dot.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DotEdgeStmt extends PsiElement {

  @Nullable
  DotAttrList getAttrList();

  @NotNull
  DotEdgeRHS getEdgeRHS();

  @Nullable
  DotNodeId getNodeId();

  @Nullable
  DotSubGraph getSubGraph();

}
