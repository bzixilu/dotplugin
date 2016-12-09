package org.plugin.dot;

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;

/**
 * Created by bzixilu on 27.11.16.
 */
public class DotAnnotatorTest extends LightCodeInsightFixtureTestCase {
    // TODO: understand and write the test
    public void testAnnotator() {
        myFixture.configureByFiles("AnnotatorTestData.dot", "DefaultTestData.simple");
        myFixture.checkHighlighting(false, false, true, true);
    }
}
