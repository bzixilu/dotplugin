package org.plugin.dot.formatter;

import com.intellij.formatting.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.formatter.common.AbstractBlock;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.plugin.dot.psi.DotTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DotBlock extends AbstractBlock {
    private SpacingBuilder spacingBuilder;

    DotBlock(@NotNull ASTNode node, @Nullable Wrap wrap, @Nullable Alignment alignment, SpacingBuilder spacingBuilder) {
        super(node, wrap, alignment);
//        System.out.println(node.getElementType().toString());
        this.spacingBuilder = spacingBuilder;
    }

    @Override
    protected List<Block> buildChildren() {
        List<Block> blocks = new ArrayList<>();
        ASTNode child = myNode.getFirstChildNode();
        while (child != null) {
            if (child.getElementType() != TokenType.WHITE_SPACE) {
                Block block = new DotBlock(child, Wrap.createWrap(WrapType.NONE, false), null, this.spacingBuilder);
                if (block.getTextRange().getLength() > 0) {
                    blocks.add(block);
                }
            }
            child = child.getTreeNext();
        }

        return blocks;
    }


    private static Set<IElementType> roots = Set.of(DotTypes.GRAPH_, DotTypes.DIGRAPH_);

    @Override
    public Indent getIndent() {
        if (myNode.getText().isBlank()) {
            return Indent.getNoneIndent();
        }
        if (roots.contains(myNode.getElementType())) {
            return Indent.getNoneIndent();
        }

        ASTNode parent = myNode.getTreeParent();
        if (parent == null) {
            return Indent.getNoneIndent();
        }

        if( myNode.getElementType().equals(DotTypes.STMT)) {
            return Indent.getNormalIndent();
        }

        return Indent.getNoneIndent();
    }

    @Override
    public Wrap getWrap() {
        ASTNode myParent = myNode.getTreeParent();
        if (myParent != null && myParent.getElementType().equals(DotTypes.STMT_LIST)) {
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
        return this.spacingBuilder.getSpacing(this, child1, child2);
    }

    @Override
    public boolean isLeaf() {
        return myNode.getFirstChildNode() == null;
    }
}
