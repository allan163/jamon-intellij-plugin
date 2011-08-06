package org.jamon.intellij.lang;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/6/11
 * Time: 12:32 AM
 */
public class JamonArgsImpl extends JamonElementImpl implements PsiElement {
    public JamonArgsImpl(ASTNode node) {
        super(node);
    }
}
