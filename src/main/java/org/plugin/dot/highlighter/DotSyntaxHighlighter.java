package org.plugin.dot.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.DotLexerAdapter;
import org.plugin.dot.psi.DotTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class DotSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey VAR =
            createTextAttributesKey("NUMERIC_VAR", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE);
    private static final TextAttributesKey NUMERIC =
            createTextAttributesKey("DOT_VAR", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey KEYWORD =
            createTextAttributesKey("DOT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey EDGE_OP =
            createTextAttributesKey("DOT_EDGE_OP", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("DOT_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    private static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("DOT_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);
    public static final TextAttributesKey COMPASS_KEY =
            createTextAttributesKey("DOT_COMPASS", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey BRACKET_KEY =
            createTextAttributesKey("DOT_BRACKET", DefaultLanguageHighlighterColors.STRING);

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] VARS_KEYS = new TextAttributesKey[]{VAR};
    private static final TextAttributesKey[] NUMERIC_KEYS = new TextAttributesKey[]{NUMERIC};
    private static final TextAttributesKey[] KEYWORD_KEYS = new TextAttributesKey[]{KEYWORD};
    private static final TextAttributesKey[] EDGE_OP_KEYS = new TextAttributesKey[]{EDGE_OP};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];
    private static final TextAttributesKey[] COMPASS_KEYS = new TextAttributesKey[]{COMPASS_KEY};
    private static final TextAttributesKey[] BRACKET_KEYS = new TextAttributesKey[]{BRACKET_KEY};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new DotLexerAdapter();
    }

    /**
     * The methods determines the text attributes for the token provided as arg
     * @param tokenType
     * @return text attributes required to highlight the corresponding token
     */
    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(DotTypes.EXTENDED_ID)| tokenType.equals(DotTypes.SIMPLE_ID)) {
            return VARS_KEYS;

        } else if (tokenType.equals(DotTypes.NUMERAL_ID)){
            return NUMERIC_KEYS;
        } else if (tokenType.equals(DotTypes.GRAPH_)
                | tokenType.equals(DotTypes.NODE_)
                | tokenType.equals(DotTypes.EDGE_)
                | tokenType.equals(DotTypes.STRICT_)
                | tokenType.equals(DotTypes.DIGRAPH_)) {
            return KEYWORD_KEYS;
        } else if (tokenType.equals(DotTypes.COMPASS)) {
            return COMPASS_KEYS;
        } else if(tokenType.equals(DotTypes.BRACHET_LEFT)|
                tokenType.equals(DotTypes.BRACKET_RIGHT)|
                tokenType.equals(DotTypes.CURLY_BRACHET_LEFT)|
                tokenType.equals(DotTypes.CURLY_BRACKET_RIGHT)){
            return BRACKET_KEYS;

        } else if (tokenType.equals(DotTypes.EDGE_OP)) {
            return EDGE_OP_KEYS;
        } else if (tokenType.equals(DotTypes.COMMENT) | tokenType.equals(DotTypes.MULTILINE_COMMENT)) {
            return COMMENT_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}

