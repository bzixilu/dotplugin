package org.plugin.dot;

import com.intellij.testFramework.ParsingTestCase;

public class DotParsingTest extends ParsingTestCase {
  public DotParsingTest() {
    super("", "dot", new DotParserDefinition());
  }

  public void testParsingTestData() {
    doTest(true);
  }

  public void testNumeralIDTestData() {
    doTest(true);
  }

  public void testBigOneTestData() {
    doTest(false);
  }

  public void testGroupedNodesTestData() {
    doTest(false);
  }

  public void testSubgraphTestData() {
    doTest(false);
  }


  @Override
  protected String getTestDataPath() {
    return "testData";
  }

  @Override
  protected boolean skipSpaces() {
    return false;
  }

  @Override
  protected boolean includeRanges() {
    return true;
  }
}
