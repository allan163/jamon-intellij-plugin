package org.jamon.intellij.lang;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import org.jamon.intellij.component.JamonPlugin;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/6/11
 * Time: 12:32 AM
 */
public class JamonElementImpl extends ASTWrapperPsiElement implements PsiElement {
    public JamonElementImpl(@org.jetbrains.annotations.NotNull ASTNode node) {
        super(node);
    }

    public Language getLanguage() {
        return JamonPlugin.JAMON.getLanguage();
    }

}
