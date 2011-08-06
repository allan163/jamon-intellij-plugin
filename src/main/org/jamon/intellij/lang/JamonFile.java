package org.jamon.intellij.lang;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jamon.intellij.component.JamonPlugin;
import org.jetbrains.annotations.NotNull;


/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 11:48 PM
 */
public class JamonFile extends PsiFileBase implements PsiElement {
    protected JamonFile(FileViewProvider viewProvider) {
        super(viewProvider, JamonPlugin.JAMON.getLanguage());
    }

    @NotNull
    public FileType getFileType() {
        return JamonPlugin.JAMON;
    }

    @Override
    public PsiManager getManager() {
        return super.getManager();
    }
}
