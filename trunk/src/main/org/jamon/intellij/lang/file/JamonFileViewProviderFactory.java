package org.jamon.intellij.lang.file;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.FileViewProviderFactory;
import com.intellij.psi.PsiManager;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/16/11
 * Time: 9:09 PM
 */
public class JamonFileViewProviderFactory implements FileViewProviderFactory {
    @Override
    public FileViewProvider createFileViewProvider(VirtualFile file, Language language,
                                                   PsiManager manager, boolean physical) {
        return new JamonFileViewProvider(manager, file, physical);
    }
}
