package org.jamon.intellij.lang;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IFileElementType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 10/31/11
 * Time: 9:06 PM
 */
public class JamonFileElementType extends IFileElementType {
    public JamonFileElementType(Language language) {
        super(language);
    }

    @NotNull
    @Override
    public Language getLanguage() {
        return super.getLanguage();
    }
}
