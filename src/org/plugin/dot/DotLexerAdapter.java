package org.plugin.dot;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Class-adapter for dot lexer
 */
public class DotLexerAdapter extends FlexAdapter {

    public DotLexerAdapter() {
        super(new _DotLexer((Reader) null));
    }
}
