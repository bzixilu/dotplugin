package org.plugin.dot.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugin.dot.psi.DotTypes;

import java.util.ArrayList;
import java.util.List;

public class DotBlock extends AbstractBlock {
    private SpacingBuilder spacingBuilder;

    DotBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment,
             SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new DotBlock(child, Wrap.createWrap(WrapType.NONE, false), null,
                        spacingBuilder);
                if (block.getTextRange().getLength() > 0) {
                    blocks.add(block);
                }
            }
            child = child.getTreeNext();
        }

        return blocks;
    }

    /**
     * The method determines the indent of the code block based on the following rules:
     * <ul>
     * <li> Graph statement has no any indent as a base block
     * <li> Any other statements inside graph are indented: node statement, attribute statement etc
     * </ul>
     *
     * @return Indent based on type of the block
     */
    @Override
    public Indent getIndent() {
        ASTNode myParent = myNode.getTreeParent();
        if (myNode.getText().trim().length() == 0) {
            return Indent.getNoneIndent();
        }
        if (myNode.getElementType().equals(DotTypes.DIGRAPH_) || myNode.getElementType().equals(DotTypes.GRAPH_)) {
            return Indent.getNoneIndent();
        }
        if (myParent != null &&
                (myParent.getElementType().equals(DotTypes.DOTGRAPH_STMT))) {
            if(myNode.getElementType().equals(DotTypes.CURLY_BRACKET_RIGHT)){
                return Indent.getNoneIndent();
            }
            return Indent.getSpaceIndent(4);
        }
        // TODO: comment are not intended
        return Indent.getNoneIndent();
    }

    @Override
    public Wrap getWrap() {
        ASTNode myParent = myNode.getTreeParent();
        if(myParent != null  && myParent.getElementType().equals(DotTypes.STMT_LIST)){
            return Wrap.createWrap(WrapType.ALWAYS, false);
        }
        return Wrap.createWrap(WrapType.NONE, false);
    }

    @Override
    public boolean isIncomplete() {
        // TODO: it's placed here to resolve problem with indentation of cursor in new line
        // But it should be implemented in proper way
        return false;
    }

    @NotNull
    @Override
    public ChildAttributes getChildAttributes(int newChildIndex) {
        return new ChildAttributes(Indent.getNoneIndent(), null);
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
