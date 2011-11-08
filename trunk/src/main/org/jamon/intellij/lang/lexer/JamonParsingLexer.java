package org.jamon.intellij.lang.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:21 PM
 */
public class JamonParsingLexer extends FlexAdapter {
    public JamonParsingLexer() {
        super(new JamonLexer((Reader) null));
    }
}
