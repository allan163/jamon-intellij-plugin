package org.jamon.intellij.lang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.tree.IElementType;
import org.jamon.intellij.lang.lexer.JamonTokenTypes;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:49 PM
 */
public class StatementParsing {
    private static Logger LOGGER = Logger.getInstance(StatementParsing.class.getSimpleName());
    public static void parseSourceElement(PsiBuilder builder) {
        IElementType token = builder.getTokenType();

        if (token != JamonTokenTypes.BAD_CHARACTER) {
            PsiBuilder.Marker statement = builder.mark();
            statement.done(token);
        }

        builder.advanceLexer();
    }
        // TODO: Implement statement parsing
}
