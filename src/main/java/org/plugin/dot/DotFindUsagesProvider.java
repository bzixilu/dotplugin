package org.plugin.dot;

import com.intellij.lang.cacheBuilder.*;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.*;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.*;
import org.plugin.dot.psi.DotId;
import org.plugin.dot.psi.DotTypes;

/**
 * Class provides dot find usages features for dot language.
 * <p>
 * At the moment find usage support is available only for DotIds elements
 * since only DotID element is named element in dot language at the moment
 * This can be changed and extended in future.
 */
public class DotFindUsagesProvider implements FindUsagesProvider {
    @Nullable
    @Override
    public WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(new DotLexerAdapter(),
                TokenSet.create(DotTypes.ID),
                TokenSet.create(DotTypes.COMMENT),
                TokenSet.EMPTY);
    }

    /**
     * The method checks whether provided psi element can be used to find its usages or not.
     * <p>
     * At the moment find usage support is available only for DotIds elements
     * since only DotID element is named element in dot language at the moment
     * This can be changed and extended in future.
     *
     * @param psiElement
     * @return boolean
     */
    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof PsiNamedElement;
    }

    /**
     * No help id is available, method returns null at the moment.
     * This can be changed in future
     *
     * @param psiElement
     * @return null
     */
    @Nullable
    @Override
    public String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    /**
     * Method returns empty string in the case of all elements except of DotID
     * In the case of DotId the method return DotID type.
     *
     * @param element
     * @return empty string or DotId type string
     */
    @NotNull
    @Override
    public String getType(@NotNull PsiElement element) {
        if (element instanceof DotId) {
            return "dot ID";
        } else {
            return "";
        }
    }

    /**
     * Method returns empty string in the case of all elements except of DotID
     * In the case of DotId the method return DotID descriptive name.
     *
     * @param element
     * @return empty string or DotID descriptive name
     */
    @NotNull
    @Override
    public String getDescriptiveName(@NotNull PsiElement element) {
        if (element instanceof DotId) {
            return ((DotId) element).getText();
        } else {
            return "";
        }
    }

    /**
     * Method returns empty string in the case of all elements except of DotID
     * In the case of DotId the method return DotID text.
     *
     * @param element
     * @return empty string or DotID text
     */
    @NotNull
    @Override
    public String getNodeText(@NotNull PsiElement element, boolean useFullName) {
        if (element instanceof DotId) {
            return ((DotId) element).getText();
        } else {
            return "";
        }
    }
}
