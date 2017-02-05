package org.plugin.dot;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;
import org.plugin.dot.psi.DotId;
import org.plugin.dot.psi.impl.DotDotgraphStmtImpl;
import static org.plugin.dot.DotPSITreeUtil.getNotMentionedNodeIds;


/**
 * Dot annotator highlights and annotates dot language code based on following rule:
 * all nodes which are used in edges but not declared explicitly will be
 * annotated as "No such such node specified in graph"
 */
public class DotAnnotator implements Annotator {

    /**
     * The method annotates all nodes which are used in edges but not declared explicitly
     *
     * @param element element you would like to annotate
     * @param holder  annotation holder
     */
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof DotDotgraphStmtImpl) {
            for (DotId id : getNotMentionedNodeIds(element)) {
                TextRange range = new TextRange(id.getTextRange().getStartOffset(),
                        id.getTextRange().getEndOffset());
                Annotation annotation = holder.createInfoAnnotation(range, "No such such node specified in graph");
                annotation.setHighlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL);
            }

        }

    }
}
