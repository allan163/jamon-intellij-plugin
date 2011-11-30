package org.jamon.intellij.lang.file;

import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.highlighter.EditorHighlighter;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.templateLanguages.TemplateLanguage;
import org.jamon.intellij.lang.JamonLanguage;
import org.jamon.intellij.lang.highlighter.JamonEditorHighlighter;
import org.jamon.intellij.lang.highlighter.JamonSyntaxHighlighter;
import org.jamon.intellij.resource.JamonIconProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/3/11
 * Time: 8:33 PM
 */
public class JamonFileType extends LanguageFileType implements TemplateLanguage {
    public static final JamonFileType JAMON_FILE_TYPE = new JamonFileType();
    public static final Language JAMON_LANGUAGE = JAMON_FILE_TYPE.getLanguage();
    public static final String DEFAULT_EXTENSION = "jamon";

    private JamonFileType() {
        super(new JamonLanguage());
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
        return DEFAULT_EXTENSION;
    }

    public Icon getIcon() {
        return JamonIconProvider.JAMON_ICON_16;
    }

    @Override
    public EditorHighlighter getEditorHighlighter(@Nullable Project project, @Nullable VirtualFile virtualFile, @NotNull EditorColorsScheme colors) {
        return new JamonEditorHighlighter(colors);
    }
}
