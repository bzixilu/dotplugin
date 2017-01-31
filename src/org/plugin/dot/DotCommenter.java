package org.plugin.dot;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

/**
 * Dot commenter provides several types of comments:
 * <lu>
 * <li>Single line comment: "//"</li>
 * <li>Multiline comment: "\/* ....  *\/"</li>
 * </lu>
 */
public class DotCommenter implements Commenter {

    /**
     * Method returns single-line comment prefix: "//"
     *
     * @return single-line comment prefix: "//"
     */
    @Nullable
    @Override
    public String getLineCommentPrefix() {
        return "//";
    }

    /**
     * Method returns multi-line comment prefix: "//"
     *
     * @return multi-line comment prefix: "/*"
     */
    @Nullable
    @Override
    public String getBlockCommentPrefix() {
        return "/*";
    }

    /**
     * Method returns multi-line comment suffix: "*\/"
     *
     * @return multi-line comment suffix: "*\/"
     */
    @Nullable
    @Override
    public String getBlockCommentSuffix() {
        return "*/";
    }

    /**
     * Method returns multi-line comment prefix: "*"
     *
     * @return null
     */
    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    /**
     * Method returns multi-line comment prefix: ""
     *
     * @return null
     */
    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }

}