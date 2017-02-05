package org.plugin.dot;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.parser.DotParser;
import org.plugin.dot.psi.DotFile;
import org.plugin.dot.psi.DotTypes;

/**
 * Class defines dot parser
 */
public class DotParserDefinition implements ParserDefinition {
    private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    private static final TokenSet COMMENTS = TokenSet.create(DotTypes.COMMENT);
    private static final TokenSet DIGRAPH = TokenSet.create(DotTypes.DIGRAPH_);
    private static final TokenSet SUBGRAPH = TokenSet.create(DotTypes.SUB_GRAPH);
    private static final TokenSet NODE = TokenSet.create(DotTypes.NODE_);
    private static final TokenSet EDGE = TokenSet.create(DotTypes.EDGE_);
    private static final TokenSet ID = TokenSet.create(DotTypes.ID);
    private static final TokenSet NODE_ID = TokenSet.create(DotTypes.NODE_ID);
    private static final TokenSet STRICT = TokenSet.create(DotTypes.STRICT_);

    private static final IFileElementType FILE =
            new IFileElementType(Language.<DotLanguage>findInstance(DotLanguage.class));

    /**
     * Getter for token set WHITE_SPACES
     *
     * @return token set WHITE_SPACES
     */
    @NotNull
    public static TokenSet getWhiteSpaces() {
        return WHITE_SPACES;
    }

    /**
     * Getter for token set COMMENTS
     *
     * @return token set COMMENTS
     */
    @NotNull
    public static TokenSet getCOMMENTS() {
        return COMMENTS;
    }

    /**
     * Getter for token set DIGRAPH
     *
     * @return token set DIGRAPH
     */
    @NotNull
    public static TokenSet getDIGRAPH() {
        return DIGRAPH;
    }

    /**
     * Getter for token set SUBGRAPH
     *
     * @return token set SUBGRAPH;
     */
    @NotNull
    public static TokenSet getSUBGRAPH() {
        return SUBGRAPH;
    }

    /**
     * Getter for token set NODE
     *
     * @return token set NODE
     */
    @NotNull
    public static TokenSet getNODE() {
        return NODE;
    }

    /**
     * Getter for token set EDGE
     *
     * @return token set EDGE
     */
    @NotNull
    public static TokenSet getEDGE() {
        return EDGE;
    }

    /**
     * Getter for token set ID
     *
     * @return token set ID
     */
    @NotNull
    public static TokenSet getID() {
        return ID;
    }

    /**
     * Getter for token set NODE_ID
     *
     * @return token set NODE_ID
     */
    @NotNull
    public static TokenSet getNodeId() {
        return NODE_ID;
    }

    /**
     * Getter for token set STRICT
     *
     * @return token set STRICT
     */
    @NotNull
    public static TokenSet getSTRICT() {
        return STRICT;
    }

    /**
     * Getter for dot file element
     *
     * @return dot file element
     */
    @NotNull
    public static IFileElementType getFILE() {
        return FILE;
    }

    /**
     * Method creates lexer which will be used to brake contents of a dot file
     * in the project into tokens
     *
     * @param project lexer will be used in
     * @return lexer for dot language
     */
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new DotLexerAdapter();
    }

    /**
     * Method returns the same as getWhiteSpaces() method
     *
     * @return WHITE_SPACES tokens
     */
    @NotNull
    public TokenSet getWhitespaceTokens() {
        return getWhiteSpaces();
    }

    /**
     * Method returns the same as getCommentTokens() method
     *
     * @return COMMENTS tokens
     */
    @NotNull
    public TokenSet getCommentTokens() {
        return getCOMMENTS();
    }

    /**
     * Return token set EMPTY, no string literal elements is available
     *
     * @return EMPTY
     */
    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    /**
     * Method creates parser which will be used for dot AST construction
     * in the project into tokens
     *
     * @param project project parser will be used in
     * @return parser for dot language
     */
    @NotNull
    public PsiParser createParser(final Project project) {
        return new DotParser();
    }

    /**
     * Getter for dot language file node type
     *
     * @return file
     */
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    /**
     * Method creates dot file based on file view provider
     *
     * @param viewProvider file view provider used for dot file creation
     * @return dot file
     */
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new DotFile(viewProvider);
    }

    /**
     * The method returns MAY at the moment. It can be changes in future
     *
     * @param left  - left node in AST
     * @param right - right node in AST
     * @return MAY
     */
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    /**
     * Method creates psi element based on provided AST node
     *
     * @param node - ast node used for psi element creation
     * @return psi element
     */
    @NotNull
    public PsiElement createElement(ASTNode node) {
        return DotTypes.Factory.createElement(node);
    }
}