package org.plugin.dot;

import com.intellij.application.options.CodeStyle;
import com.intellij.openapi.application.PluginPathManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import com.intellij.testFramework.fixtures.LightJavaCodeInsightFixtureTestCase;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;

/**
 * Created by bzixilu on 29.01.17.
 */
public class DotFormatterTest extends BasePlatformTestCase {
    @NotNull
    @Override
    protected String getTestDataPath() {
        return "testData";
    }


    public void testFormatter() {
        myFixture.configureByFiles("FormatterTestData.dot");

        // everything is turned on test
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AROUND_EQUALITY_OPERATORS = true;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_BEFORE_CLASS_LBRACE = true;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_BEFORE_METHOD_LBRACE = true;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_WITHIN_BRACKETS = true;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AFTER_SEMICOLON = true;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_BEFORE_SEMICOLON = true;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AFTER_COLON = true;

        WriteCommandAction.writeCommandAction(getProject()).run(() ->
                CodeStyleManager
                        .getInstance(getProject())
                        .reformatText(
                                myFixture.getFile(),
                                ContainerUtil.newArrayList(
                                        myFixture.getFile().getTextRange()
                                )
                        )
        );
        myFixture.checkResultByFile("FormatterTestDataFormatted.dot");
    }

    public void testFormatterTurnedOff() {
        myFixture.configureByFiles("FormatterTestData.dot");

        // everything is turned off test
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AROUND_ASSIGNMENT_OPERATORS = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AFTER_SEMICOLON = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AROUND_EQUALITY_OPERATORS = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_BEFORE_CLASS_LBRACE = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_BEFORE_METHOD_LBRACE = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_WITHIN_BRACKETS = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_BEFORE_CLASS_LBRACE = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_BEFORE_METHOD_LBRACE = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_WITHIN_BRACKETS = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AFTER_SEMICOLON = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_BEFORE_SEMICOLON = false;
        CodeStyle.getLanguageSettings(myFixture.getFile()).SPACE_AFTER_COLON = false;
        new WriteCommandAction.Simple(getProject()) {
            @Override
            protected void run() throws Throwable {
                CodeStyleManager.getInstance(getProject()).reformatText(myFixture.getFile(),
                        ContainerUtil.newArrayList(myFixture.getFile().getTextRange()));
            }
        }.execute();
        myFixture.checkResultByFile("FormatterTestDataTurnedOff.dot");
    }
}
