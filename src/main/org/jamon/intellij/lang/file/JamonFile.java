package org.jamon.intellij.lang.file;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;


/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 11:48 PM
 */
public class JamonFile extends PsiFileBase implements PsiElement {
    public JamonFile(FileViewProvider viewProvider) {
        super(viewProvider, JamonFileType.JAMON_LANGUAGE);
    }

    @NotNull
    public FileType getFileType() {
        return JamonFileType.JAMON_FILE_TYPE;
    }

    @Override
    public PsiManager getManager() {
        return super.getManager();
    }
}
