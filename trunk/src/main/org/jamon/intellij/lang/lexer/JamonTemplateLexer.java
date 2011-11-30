package org.jamon.intellij.lang.lexer;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LookAheadLexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/16/11
 * Time: 10:30 PM
 */
public class JamonTemplateLexer extends LookAheadLexer {
    private static Logger LOGGER = Logger.getInstance(JamonTemplateLexer.class.getSimpleName());
    public JamonTemplateLexer() {
        super(new JamonLexer());
    }

    @Override
    protected void lookAhead(Lexer baseLexer) {
        final IElementType nextType = baseLexer.getTokenType();
        if (nextType != null) {
            LOGGER.warn("nextType: " + nextType.toString());
        }
        // todo: this is where we need to add the TEMPLATE_TEXT element somehow.... im so tired.
        super.lookAhead(baseLexer);
    }
}
