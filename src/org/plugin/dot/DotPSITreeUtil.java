package org.plugin.dot;

import com.intellij.lang.parser.GeneratedParserUtilBase;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiRecursiveElementVisitor;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.indexing.FileBasedIndex;
import org.plugin.dot.filetypes.DotFileType;
import org.plugin.dot.psi.DotEdgeStmt;
import org.plugin.dot.psi.DotFile;
import org.plugin.dot.psi.DotId;
import org.plugin.dot.psi.DotNodeStmt;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Just utility class containing different methods-helpers for PSI tree navigation, searching etc
 */
public class DotPSITreeUtil extends GeneratedParserUtilBase {

    /**
     * Utility method returned all children nodes which have are mentioned in
     * edge statements but not mentioned in node statements.
     * Case when node is used in edge but not initialized earlier
     * @param e - PSI element (root element for sub tree  for searching nodes)
     * @return - iterable entity containing used but mentioned nodes
     */
    // TODO: probably it makes sense to change PsiElement to GraphStmt
    public static Iterable<DotId> getNotMentionedNodeIds(PsiElement e) {
        Set<DotId> nodeIds = new HashSet<>();
        Set<DotId> edgeIds = new HashSet<>();
        Set<DotId> result = new HashSet<>();
        PsiRecursiveElementVisitor psiRecursiveElementVisitor = new PsiRecursiveElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                if (element instanceof DotNodeStmt) {
                    nodeIds.add(((DotNodeStmt) element).getNodeId().getId());
                } else if (element instanceof DotEdgeStmt) {
                    edgeIds.add(((DotEdgeStmt) element).getNodeId().getId());
                    edgeIds.add(((DotEdgeStmt) element).getEdgeRHS().getNodeId().getId());
                }
                super.visitElement(element);
            }
        };
        psiRecursiveElementVisitor.visitElement(e);

        for(DotId edgeId: edgeIds){
            if (nodeIds.stream().noneMatch((i) -> edgeId.getText().equals(i.getText()))){
                result.add(edgeId);
            }
        }
        return result;
    }

    public static Iterable<DotId> findDotIds(Project project, String id_) {
        Set<DotId> ids = new HashSet<>();
        PsiRecursiveElementVisitor psiRecursiveElementVisitor = new PsiRecursiveElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                if (element instanceof DotId && element.getText().equals(id_)) {
                    ids.add(((DotId) element));
                }
                super.visitElement(element);
            }
        };
        Collection<VirtualFile> virtualFiles =
                FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, DotFileType.INSTANCE,
                        GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            DotFile dotFile = (DotFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (dotFile != null) {
                for (PsiElement e: dotFile.getChildren()) {
                    psiRecursiveElementVisitor.visitElement(e);
                }
            }
        }
        return ids;
    }

    public static Iterable<DotId> findDotIds(Project project) {
        Set<DotId> ids = new HashSet<>();
        PsiRecursiveElementVisitor psiRecursiveElementVisitor = new PsiRecursiveElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                if (element instanceof DotId) {
                    ids.add(((DotId) element));
                }
                super.visitElement(element);
            }
        };
        Collection<VirtualFile> virtualFiles =
                FileBasedIndex.getInstance().getContainingFiles(FileTypeIndex.NAME, DotFileType.INSTANCE,
                        GlobalSearchScope.allScope(project));
        for (VirtualFile virtualFile : virtualFiles) {
            DotFile dotFile = (DotFile) PsiManager.getInstance(project).findFile(virtualFile);
            if (dotFile != null) {
                for (PsiElement e: dotFile.getChildren()) {
                    psiRecursiveElementVisitor.visitElement(e);
                }
            }
        }
        return ids;
    }
}
