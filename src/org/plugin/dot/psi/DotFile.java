package org.plugin.dot.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.filetypes.DotFileType;
import org.plugin.dot.DotLanguage;

import javax.swing.*;

public class DotFile extends PsiFileBase {
  public DotFile(@NotNull FileViewProvider viewProvider) {
    super(viewProvider, DotLanguage.INSTANCE);
  }

  @NotNull
  @Override
  public FileType getFileType() {
    return DotFileType.INSTANCE;
  }

  @Override
  public String toString() {
    return "Dot File";
  }

  @Override
  public Icon getIcon(int flags) {
    return super.getIcon(flags);
  }
}