package org.plugin.dot;

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
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AROUND_ASSIGNMENT_OPERATORS = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AFTER_SEMICOLON = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AROUND_EQUALITY_OPERATORS = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_CLASS_LBRACE = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_METHOD_LBRACE = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_WITHIN_BRACKETS= true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_CLASS_LBRACE = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_METHOD_LBRACE = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_WITHIN_BRACKETS= true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AFTER_SEMICOLON = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_SEMICOLON = true;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AFTER_COLON = true;
        new WriteCommandAction.Simple(getProject()) {
            @Override
            protected void run() throws Throwable {
                CodeStyleManager.getInstance(getProject()).reformatText(myFixture.getFile(),
                        ContainerUtil.newArrayList(myFixture.getFile().getTextRange()));
            }
        }.execute();
        myFixture.checkResultByFile("FormatterTestDataFormatted.dot");
    }

    public void testFormatterTurnedOff() {
        myFixture.configureByFiles("FormatterTestData.dot");

        // everything is turned off test
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AROUND_ASSIGNMENT_OPERATORS = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AFTER_SEMICOLON = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AROUND_EQUALITY_OPERATORS = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_CLASS_LBRACE = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_METHOD_LBRACE = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_WITHIN_BRACKETS= false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_CLASS_LBRACE = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_METHOD_LBRACE = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_WITHIN_BRACKETS= false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AFTER_SEMICOLON = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_BEFORE_SEMICOLON = false;
        CodeStyleSettingsManager.getSettings(getProject()).SPACE_AFTER_COLON = false;
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
