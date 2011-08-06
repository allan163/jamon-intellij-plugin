package org.jamon.intellij.lang;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jamon.intellij.component.JamonPlugin;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/3/11
 * Time: 8:33 PM
 */
public class JamonFileType extends LanguageFileType {
    public static final String EXTENSION = "jamon";
    public JamonFileType() {
        super(JamonPlugin.JAMON_LANGUAGE);
    }

    @NotNull
    public String getName() {
        return "Jamon";
    }

    @NotNull
    public String getDescription() {
        return "Jamon";
    }

    @NotNull
    public String getDefaultExtension() {
        return "jamon";
    }

    public Icon getIcon() {
        return null;
    }
}
