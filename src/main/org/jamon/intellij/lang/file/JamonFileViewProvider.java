package org.jamon.intellij.lang.file;

import com.intellij.lang.Language;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider;
import com.intellij.psi.PsiManager;
import com.intellij.psi.templateLanguages.TemplateLanguageFileViewProvider;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/16/11
 * Time: 9:08 PM
 */
public class JamonFileViewProvider extends MultiplePsiFilesPerDocumentFileViewProvider implements TemplateLanguageFileViewProvider {
    public JamonFileViewProvider(PsiManager manager, VirtualFile virtualFile, boolean physical) {
        super(manager, virtualFile, physical);
    }

    @NotNull
    @Override
    public Language getBaseLanguage() {
        return JamonFileType.JAMON_LANGUAGE;
    }

    @NotNull
    @Override
    public Language getTemplateDataLanguage() {
        return JamonFileType.JAMON_LANGUAGE;
    }

    @Override
    protected MultiplePsiFilesPerDocumentFileViewProvider cloneInner(VirtualFile fileCopy) {
        // todo: is this all that's needed here?
        return new JamonFileViewProvider(getManager(), fileCopy, isPhysical());
    }
}
