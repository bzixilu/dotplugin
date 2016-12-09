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

/**
 * Dot color setting page providing color settings for following dot tokens:
 * <p>
 * <ul>
 * <li>Edge operation</li>
 * <li>Comment</li>
 * <li>Keywords: graph, node, edge, strict, digraph</li>
 * <li>Compass</li>
 * <li>Brackets</li>
 * <li>Identifiers</li>
 * </ul>
 */
public class DotColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Edge Operation", DotSyntaxHighlighter.EDGE_OP),
            new AttributesDescriptor("Comment", DotSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("Keyword", DotSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("Compass key", DotSyntaxHighlighter.COMPASS_KEY),
            new AttributesDescriptor("Brackets", DotSyntaxHighlighter.BRACKET_KEY),
            new AttributesDescriptor("ID", DotSyntaxHighlighter.VAR)
    };

    /**
     * Icon getter
     *
     * @return icon
     */
    @Nullable
    @Override
    public Icon getIcon() {
        return DotIcons.FILE;
    }

    /**
     * SyntaxHighlighter getter
     *
     * @return syntax highlighter
     */
    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new DotSyntaxHighlighter();
    }

    /**
     * The method provides demo text, used as demo in color settings page for color scheme
     * demonstration
     *
     * @return string
     */
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
    }

    ;

    /**
     * The method returns null since no additional highlighting tag to description map exists
     *
     * @return null
     */
    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    /**
     * Get attribute descriptors which can be tuned in color settings page
     *
     * @return descriptors
     */
    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    /**
     * Method returns empty color descriptors array, no information is available
     *
     * @return empty array
     */
    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    /**
     * Method returns display name for language is being tuning in color settings page
     *
     * @return string "Dot"
     */
    @NotNull
    @Override
    public String getDisplayName() {
        return "Dot";
    }
}