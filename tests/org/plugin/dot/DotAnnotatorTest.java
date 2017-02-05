package org.plugin.dot;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

import java.nio.file.Paths;

/**
 * Created by bzixilu on 27.11.16.
 */
public class DotAnnotatorTest extends LightCodeInsightFixtureTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    public void testAnnotator() {
        myFixture.configureByFiles("AnnotatorTestData.dot");
        myFixture.checkHighlighting(false, false, true, true);
    }
}
