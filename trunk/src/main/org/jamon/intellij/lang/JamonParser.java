package org.jamon.intellij.lang;

import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiParser;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:33 PM
 */
public class JamonParser implements PsiParser {
    @NotNull
    public ASTNode parse(IElementType root, PsiBuilder builder) {
        builder.setDebugMode(true);
        final PsiBuilder.Marker rootMarker = builder.mark();
        while (!builder.eof()) {
            StatementParsing.parseSourceElement(builder);
        }
        rootMarker.done(root);
        return builder.getTreeBuilt();
    }
}
