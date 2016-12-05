package org.plugin.dot.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import org.plugin.dot.filetypes.DotFileType;

public class DotElementFactory {

  public static PsiElement createCRLF(Project project) {
    final DotFile file = createFile(project, "\n");
    return file.getFirstChild();
  }

  public static DotFile createFile(Project project, String text) {
    String name = "dummy.dot";
    return (DotFile) PsiFileFactory.getInstance(project).
        createFileFromText(name, DotFileType.INSTANCE, text);
  }

  public static DotId createDotId(Project project, String newName) {
    final DotFile file = createFile(project, "digraph "  + newName + "{}");
    return ((DotDotgraphStmt)file.getFirstChild()).getId();
  }
}