package org.plugin.dot;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugin.dot.highlighter.DotSyntaxHighlighter;

import javax.swing.*;
import java.util.Map;

public class DotColorSettingsPage implements ColorSettingsPage {

  private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
      new AttributesDescriptor("Edge Operation", DotSyntaxHighlighter.EDGE_OP),
      new AttributesDescriptor("Comment", DotSyntaxHighlighter.COMMENT),
      new AttributesDescriptor("Keyword", DotSyntaxHighlighter.KEYWORD),
      new AttributesDescriptor("Compass key", DotSyntaxHighlighter.COMPASS_KEY),
      new AttributesDescriptor("Brackets", DotSyntaxHighlighter.BRACKET_KEY),
      new AttributesDescriptor("ID", DotSyntaxHighlighter.VAR)
  };

  @Nullable
  @Override
  public Icon getIcon() {
    return DotIcons.FILE;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new DotSyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "# You are reading the \".dot or .gv\" entry.\n" +
            "graph graphname {\n" +
            "// This attribute applies to the graph itself\n" +
            "size=\"1,1\";\n" +
            "// The label attribute can be used to change the label of a node\n" +
            "a [label=\"Foo\"];\n" +
            "// Here, the node shape is changed.\n" +
            "b [shape=box];\n" +
            "// These edges both have different line properties\n" +
            "a -- b -- c [color=blue];\n" +
            "b -- d [style=dotted];\n" +
            "// [style=invis] hides a node.\n";
  };


  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return DESCRIPTORS;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "Dot";
  }
}