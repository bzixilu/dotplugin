package org.plugin.dot.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugin.dot.psi.DotEdgeStmt;
import org.plugin.dot.psi.DotStmtList;
import org.plugin.dot.psi.DotTokenType;
import org.plugin.dot.psi.DotTypes;
import org.plugin.dot.psi.impl.DotDotgraphStmtImpl;
import org.plugin.dot.psi.impl.DotStmtListImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DotBlock extends AbstractBlock {
    private SpacingBuilder spacingBuilder;

    protected DotBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
                       SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<Block>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new DotBlock(child, Wrap.createWrap(WrapType.NONE, false), Alignment.createAlignment(),
                        spacingBuilder);
                if (block.getTextRange().getLength() > 0) {
                    blocks.add(block);
                }
            }
            child = child.getTreeNext();
        }
        return blocks;
    }

    @Override
    public Indent getIndent() {
        if (myNode.getText().trim().length() == 0) {
            return Indent.getNoneIndent();
        }
        if(myNode.getElementType().equals(DotTypes.DOTGRAPH_STMT)){
            return Indent.getNoneIndent();
        }
        if (myNode.getElementType().equals(DotTypes.STMT)){
            return Indent.getNormalIndent(false);
        }
        // TODO: comment are not intended
        return Indent.getNoneIndent();
    }

    @Nullable
    @Override
    public Spacing getSpacing(@Nullable Block child1, @NotNull Block child2) {
        return spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
