package org.plugin.dot.psi.impl;

import com.intellij.openapi.project.ProjectManager;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.PsiTestCase;
import org.junit.Test;
import org.plugin.dot.DotIcons;
import org.plugin.dot.psi.DotElementFactory;
import org.plugin.dot.psi.DotId;

import static org.junit.Assert.*;

public class DotPsiImplUtilTest extends PsiTestCase {

    @Test
    public void testGetName() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "test_name");
        assertEquals("test_name", DotPsiImplUtil.getName(id));
    }

    @Test
    public void testSetName() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "test_name");
        PsiElement e  = DotPsiImplUtil.setName(id, "new_name");
        assertEquals("new_name", ((DotId) e).getName());
    }

    @Test
    public void testSetNameAnotherKind() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "<another_kind>");
        PsiElement e  = DotPsiImplUtil.setName(id, "new_name");
        assertEquals("new_name", ((DotId) e).getName());
    }

    @Test
    public void testGetNameIdentifier() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "test_name");
        assertEquals(id.getSimpleId().getNode().getPsi(), DotPsiImplUtil.getNameIdentifier(id));
    }

    @Test
    public void testGetNameIdentifierNumeral() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "0.124");
        assertEquals(id.getNumeralId().getNode().getPsi(), DotPsiImplUtil.getNameIdentifier(id));
    }

    @Test
    public void testGetNameIdentifierQuoted() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "\"0.124\"");
        assertEquals(id.getQuotedAny().getNode().getPsi(), DotPsiImplUtil.getNameIdentifier(id));
    }

    @Test
    public void testGetNameIdentifierHtml() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "<html>");
        assertEquals(id.getHtmlId().getNode().getPsi(), DotPsiImplUtil.getNameIdentifier(id));
    }

    @Test
    public void getPresentation() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "<html>");
        assertEquals("<html>", id.getPresentation().getPresentableText());
    }

    @Test
    public void getPresentationIcon() throws Exception {
        DotId id = DotElementFactory.createDotId(this.getProject(), "<html>");
        assertEquals(DotIcons.FILE, id.getPresentation().getIcon(false));
    }

}