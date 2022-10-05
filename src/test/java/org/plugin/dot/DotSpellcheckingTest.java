package org.plugin.dot;

import com.intellij.openapi.application.PluginPathManager;
import com.intellij.spellchecker.inspections.SpellCheckingInspection;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.io.File;

public class DotSpellcheckingTest extends BasePlatformTestCase {

    private void doSpellingTest() {
        myFixture.enableInspections(new SpellCheckingInspection());
        myFixture.configureByFile(getTestName(true) + "." + "dot");
        myFixture.testHighlighting();
    }

    @Test
    public void testSimple() {
        doSpellingTest();
    }

    @NotNull
    @Override
    protected String getTestDataPath() {
        return "testData/spellchecker";
    }
}
