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

import java.util.Collections;

import static org.plugin.dot.DotPSITreeUtil.findDotIds;
import static org.plugin.dot.DotPSITreeUtil.getNotMentionedNodeIds;

public class DotAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        if(element instanceof DotDotgraphStmtImpl){
            for(DotId id: findDotIds(element.getProject())){
                System.out.println(id);
            }
            System.out.println("------------------------");
            for(DotId id: getNotMentionedNodeIds(element)){
                TextRange range = new TextRange(id.getTextRange().getStartOffset(),
                        id.getTextRange().getStartOffset());
                Annotation annotation = holder.createInfoAnnotation(range, "No such such node specified in graph");
                annotation.setHighlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL);
            }

        }

    }
}
