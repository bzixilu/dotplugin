package org.plugin.dot.codestyle;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

public class DotCodeStyleSettings extends CustomCodeStyleSettings {
  public DotCodeStyleSettings(CodeStyleSettings settings) {
    super("org.plugin.dot.codestyle.DotCodeStyleSettings", settings);
  }
}