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

public class DotParserDefinition implements ParserDefinition {
    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final TokenSet COMMENTS = TokenSet.create(DotTypes.COMMENT);


    public static final TokenSet DIGRAPH = TokenSet.create(DotTypes.DIGRAPH_);
    public static final TokenSet SUBGRAPH = TokenSet.create(DotTypes.SUB_GRAPH);
    public static final TokenSet NODE = TokenSet.create(DotTypes.NODE_);
    public static final TokenSet EDGE = TokenSet.create(DotTypes.EDGE_);
    public static final TokenSet ID = TokenSet.create(DotTypes.ID);
    public static final TokenSet NODE_ID = TokenSet.create(DotTypes.NODE_ID);
    public static final TokenSet STRICT = TokenSet.create(DotTypes.STRICT_);

    public static final IFileElementType FILE =
            new IFileElementType(Language.<DotLanguage>findInstance(DotLanguage.class));

    @NotNull
    public static TokenSet getWhiteSpaces() {
        return WHITE_SPACES;
    }

    @NotNull
    public static TokenSet getCOMMENTS() {
        return COMMENTS;
    }

    @NotNull
    public static TokenSet getDIGRAPH() {
        return DIGRAPH;
    }

    @NotNull
    public static TokenSet getSUBGRAPH() {
        return SUBGRAPH;
    }

    @NotNull
    public static TokenSet getNODE() {
        return NODE;
    }

    @NotNull
    public static TokenSet getEDGE() {
        return EDGE;
    }

    @NotNull
    public static TokenSet getID() {
        return ID;
    }

    @NotNull
    public static TokenSet getNodeId() {
        return NODE_ID;
    }

    @NotNull
    public static TokenSet getSTRICT() {
        return STRICT;
    }

    @NotNull
    public static IFileElementType getFILE() {
        return FILE;
    }

    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return new DotLexerAdapter();
    }

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @NotNull
    public PsiParser createParser(final Project project) {
        return new DotParser();
    }

    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }

    public PsiFile createFile(FileViewProvider viewProvider) {
        return new DotFile(viewProvider);
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return DotTypes.Factory.createElement(node);
    }
}