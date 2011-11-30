package org.jamon.intellij.lang.highlighter;

import com.intellij.ide.highlighter.HtmlFileHighlighter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.ex.util.LayerDescriptor;
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import org.jamon.intellij.lang.element.JamonElementTypes;
import org.jamon.intellij.lang.lexer.JamonTemplateLexer;
import org.jamon.intellij.lang.lexer.JamonTokenTypes;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/17/11
 * Time: 12:32 AM
 */
public class JamonEditorHighlighter extends LayeredLexerEditorHighlighter {
    public JamonEditorHighlighter(EditorColorsScheme scheme) {
        super(new JamonSyntaxHighlighter() {
            @NotNull
            @Override
            public Lexer getHighlightingLexer() {
                return new JamonTemplateLexer();
            }
        }, scheme);

        LayerDescriptor descriptor = new LayerDescriptor(new HtmlFileHighlighter(), "\n");
        registerLayer(JamonTokenTypes.OUTPUT, descriptor);
    }

}
