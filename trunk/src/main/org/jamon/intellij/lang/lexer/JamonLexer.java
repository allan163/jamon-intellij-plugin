package org.jamon.intellij.lang.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:21 PM
 */
public class JamonLexer extends FlexAdapter {
    public JamonLexer() {
        super(new JamonFlexLexer((Reader) null));
    }
}
