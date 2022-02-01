package org.plugin.dot;

import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;

/**
 * Created by bzixilu on 27.11.16.
 */
public class DotAnnotatorTest extends BasePlatformTestCase {
    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    public void testAnnotator() {
        myFixture.configureByFiles("AnnotatorTestData.dot");
        myFixture.checkHighlighting(false, false, true, true);
    }
}
