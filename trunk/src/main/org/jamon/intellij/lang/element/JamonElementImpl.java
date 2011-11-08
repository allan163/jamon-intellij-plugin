package org.jamon.intellij.lang.element;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

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
}
