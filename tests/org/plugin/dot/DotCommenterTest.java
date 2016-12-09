package org.plugin.dot;

import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction;
import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase;
import org.plugin.dot.filetypes.DotFileType;

/**
 * Created by ostrizhe on 09/12/16.
 */
public class DotCommenterTest extends LightCodeInsightFixtureTestCase {
    public void testCommenter() {
        myFixture.configureByText(DotFileType.INSTANCE, "<caret>website = http://en.wikipedia.org/");
        CommentByLineCommentAction commentAction = new CommentByLineCommentAction();
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("#website = http://en.wikipedia.org/");
        commentAction.actionPerformedImpl(getProject(), myFixture.getEditor());
        myFixture.checkResult("website = http://en.wikipedia.org/");
    }
}
