package org.plugin.dot.psi.impl;

import com.intellij.openapi.application.PluginPathManager;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.plugin.dot.DotIcons;
import org.plugin.dot.psi.DotElementFactory;
import org.plugin.dot.psi.DotId;

public class DotPsiImplUtilTest extends BasePlatformTestCase {
    @NotNull
    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    @Test
    public void testGetName() {
        DotId id = DotElementFactory.createDotId(getProject(), "test_name");
        Assert.assertEquals("test_name", DotPsiImplUtil.getName(id));
    }

    @Test
    public void testSetName() {
        DotId id = DotElementFactory.createDotId(getProject(), "test_name");
        PsiElement e  = DotPsiImplUtil.setName(id, "new_name");
        Assert.assertEquals("new_name", ((DotId) e).getName());
    }

    @Test
    public void testSetNameAnotherKind() {
        DotId id = DotElementFactory.createDotId(getProject(), "<another_kind>");
        PsiElement e  = DotPsiImplUtil.setName(id, "new_name");
        Assert.assertEquals("new_name", ((DotId) e).getName());
    }

    @Test
    public void testGetNameIdentifier() {
        DotId id = DotElementFactory.createDotId(this.getProject(), "test_name");
        Assert.assertEquals(id.getSimpleId().getNode().getPsi(), DotPsiImplUtil.getNameIdentifier(id));
    }

    @Test
    public void testGetNameIdentifierNumeral() {
        DotId id = DotElementFactory.createDotId(this.getProject(), "0.124");
        Assert.assertEquals(id.getNumeralId().getNode().getPsi(), DotPsiImplUtil.getNameIdentifier(id));
    }

    @Test
    public void testGetNameIdentifierQuoted() {
        DotId id = DotElementFactory.createDotId(getProject(), "\"0.124\"");
        Assert.assertEquals(id.getQuotedAny().getNode().getPsi(), DotPsiImplUtil.getNameIdentifier(id));
    }

    @Test
    public void testGetNameIdentifierHtml() {
        DotId id = DotElementFactory.createDotId(getProject(), "<html>");
        Assert.assertEquals(id.getHtmlId().getNode().getPsi(), DotPsiImplUtil.getNameIdentifier(id));
    }

    @Test
    public void getPresentation() {
        DotId id = DotElementFactory.createDotId(this.getProject(), "<html>");
        Assert.assertEquals("<html>", id.getPresentation().getPresentableText());
    }

    @Test
    public void getPresentationIcon() {
        DotId id = DotElementFactory.createDotId(this.getProject(), "<html>");
        Assert.assertEquals(DotIcons.FILE, id.getPresentation().getIcon(false));
    }

}