package org.plugin.dot;

import com.intellij.spellchecker.inspections.SpellCheckingInspection;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

import java.io.File;

public class DotSpellcheckingTest extends LightPlatformCodeInsightFixtureTestCase {
    private void doSpellingTest(){
        myFixture.enableInspections(new SpellCheckingInspection());

        myFixture.configureByFile(getTestName(true) + "." + "dot");
        myFixture.testHighlighting();
    }

    public void testSimple(){
        doSpellingTest();
    }

    @Override
    protected String getTestDataPath() {
        return "testData" + File.separator + "spellchecker";
    }
}
