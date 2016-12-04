// This is a generated file. Not intended for manual editing.
package org.plugin.dot.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface DotStmt extends PsiElement {

  @NotNull
  List<DotId> getIdList();

  @Nullable
  DotAttrStmt getAttrStmt();

  @Nullable
  DotCommentStmt getCommentStmt();

  @Nullable
  DotEdgeStmt getEdgeStmt();

  @Nullable
  DotNodeStmt getNodeStmt();

  @Nullable
  DotSubGraph getSubGraph();

}
