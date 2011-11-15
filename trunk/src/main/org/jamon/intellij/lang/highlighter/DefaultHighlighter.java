package org.jamon.intellij.lang.highlighter;

import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.CodeInsightColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/12/11
 * Time: 4:19 PM
 */
public class DefaultHighlighter {
    public static final String BAD_CHARACTER_ID = "Bad Character";
    public static final String KEYWORD_ID = "Keyword";
    public static final String TAG_DECORATION_ID = "Tag decoration";

    public static final Color KEYWORD_COLOR = new Color(0, 0, 67);
    public static final Color TAG_DECORATION_COLOR = new Color(200, 200, 200);

    public static final TextAttributes KEYWORD_ATTRIBUTES =
            SyntaxHighlighterColors.KEYWORD.getDefaultAttributes().clone();
    public static final TextAttributes TAG_DECORATION_ATTRIBUTES =
            SyntaxHighlighterColors.BRACES.getDefaultAttributes().clone();

    static{
        KEYWORD_ATTRIBUTES.setForegroundColor(KEYWORD_COLOR);
        KEYWORD_ATTRIBUTES.setFontType(Font.BOLD);

        TAG_DECORATION_ATTRIBUTES.setBackgroundColor(TAG_DECORATION_COLOR);
    }

    public static TextAttributesKey KEYWORD =
            TextAttributesKey.createTextAttributesKey(KEYWORD_ID, KEYWORD_ATTRIBUTES);

    public static TextAttributesKey BAD_CHARACTER =
            TextAttributesKey.createTextAttributesKey(
                    DefaultHighlighter.BAD_CHARACTER_ID,
                    CodeInsightColors.UNMATCHED_BRACE_ATTRIBUTES.getDefaultAttributes());


    public static TextAttributesKey TAG_DECORATION =
            TextAttributesKey.createTextAttributesKey(TAG_DECORATION_ID, TAG_DECORATION_ATTRIBUTES);
}
