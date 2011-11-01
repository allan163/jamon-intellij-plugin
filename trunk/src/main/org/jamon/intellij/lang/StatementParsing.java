package org.jamon.intellij.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.tree.IElementType;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:49 PM
 */
public class StatementParsing {
    public static void parseSourceElement(PsiBuilder builder) {
            IElementType token = builder.getTokenType();
            PsiBuilder.Marker statement = builder.mark();
            statement.done(token);
            builder.advanceLexer();
        }
        // TODO: Implement statement parsing
}
