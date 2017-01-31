package org.plugin.dot;

import com.intellij.codeInsight.generation.actions.CommentByBlockCommentAction;
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.plugin.dot.filetypes.DotFileType;

/**
 * Created by bzixilu on 29.01.17.
 */
public class DotCommenterTest extends LightCodeInsightFixtureTestCase {

    @Override
    protected String getTestDataPath() {
        return "testData";
    }

    public void testCommenter() {
        myFixture.configureByText(DotFileType.INSTANCE, "tratata");
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("//tratata");
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("tratata");
    }

    //TODO: test fails it should be fixed
    public void testCommenterMultiline() {
        myFixture.configureByFile("CommenterMultilineTestDataInitial.dot");
        CommentByBlockCommentAction commentAction = new CommentByBlockCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResultByFile("CommenterMultilineTestData.dot");
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("CommenterMultilineTestDataInitial.dot");
    }
}
