package org.jamon.intellij.lang;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import org.jamon.intellij.lang.file.JamonFileType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 10/31/11
 * Time: 9:29 PM
 */
public class JamonWrapperPsiElement extends ASTWrapperPsiElement {
    public JamonWrapperPsiElement(@org.jetbrains.annotations.NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public Language getLanguage() {
        return JamonFileType.JAMON_LANGUAGE;
    }
}
