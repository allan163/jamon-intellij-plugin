package org.jamon.intellij.resource;

import com.intellij.ide.IconProvider;
import com.intellij.openapi.util.IconLoader;
import com.intellij.psi.PsiElement;
import org.jamon.intellij.lang.file.JamonFile;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * User: Ryan Brignoni
 * Date: 7/29/11
 * Time: 10:24 PM
 */
public class JamonIconProvider extends IconProvider {
    private static final String JAMON_16_PNG = "/org/jamon/intellij/images/jamon_16.png";
    private static final String JAMON_24_PNG = "/org/jamon/intellij/images/jamon_24.png";
    private static final String JAMON_32_PNG = "/org/jamon/intellij/images/jamon_32.png";

    public static final Icon JAMON_ICON_16 = IconLoader.getIcon(JAMON_16_PNG);
    public static final Icon JAMON_ICON_24 = IconLoader.getIcon(JAMON_24_PNG);
    public static final Icon JAMON_ICON_32 = IconLoader.getIcon(JAMON_32_PNG);

    @Override
    public Icon getIcon(@NotNull PsiElement element, int flags) {
        if (element instanceof JamonFile) {
          return JAMON_ICON_16;
        }
        return null;
    }
}
