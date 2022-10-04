package org.plugin.dot.spellchecker;

import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.spellchecker.inspections.CommentSplitter;
import com.intellij.spellchecker.inspections.PropertiesSplitter;
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy;
import com.intellij.spellchecker.tokenizer.Tokenizer;
import com.intellij.spellchecker.tokenizer.TokenizerBase;
import org.jetbrains.annotations.NotNull;
import org.plugin.dot.psi.DotCommentStmt;
import org.plugin.dot.psi.impl.DotNodeIdImpl;

public class DotSpellcheckingStrategy extends SpellcheckingStrategy {
    private static final Tokenizer DOT_NODE_TOKENIZER = TokenizerBase.create(PropertiesSplitter.getInstance());
    private static final Tokenizer DOT_COMMENT_TOKENIZER = TokenizerBase.create(CommentSplitter.getInstance());

    @NotNull
    @Override
    public Tokenizer getTokenizer(PsiElement element) {
        if (element instanceof DotNodeIdImpl) {
            return DOT_NODE_TOKENIZER;
        }
        if (element instanceof PsiComment || element instanceof DotCommentStmt) {
            return DOT_COMMENT_TOKENIZER;
        }
        return EMPTY_TOKENIZER;
    }
}
