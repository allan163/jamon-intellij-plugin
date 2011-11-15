package org.jamon.intellij.lang.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jamon.intellij.lang.lexer.JamonLexer;
import org.jamon.intellij.lang.lexer.JamonTokenTypes;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/12/11
 * Time: 4:10 PM
 */
public class JamonSyntaxHighlighter extends SyntaxHighlighterBase implements JamonTokenTypes {
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES =
            new HashMap<IElementType, TextAttributesKey>();

    public static final TokenSet BAD_CHARACTERS = TokenSet.create(
            BAD_CHARACTER
    );

    public static final TokenSet KEYWORDS = TokenSet.create(
//            ABSTRACT,
//            ARGS_START,
//            ARGS_END,
//            IMPORT_START,
//            IMPORT_END
    );

    public static final TokenSet TAG_DECORATIONS = TokenSet.create(
            JAMON_PERCENT_TAG,
            JAMON_PERCENT_TAG_CLOSE,
            PERCENT_TAG_START,
            PERCENT_TAG_CLOSE,
            PERCENT_TAG_END,
            GENERIC_TAG_END
    );

    static {
        fillMap(ATTRIBUTES, BAD_CHARACTERS, DefaultHighlighter.BAD_CHARACTER);
        fillMap(ATTRIBUTES, KEYWORDS, DefaultHighlighter.KEYWORD);
        fillMap(ATTRIBUTES, TAG_DECORATIONS, DefaultHighlighter.TAG_DECORATION);
    }

    @NotNull
    public Lexer getHighlightingLexer() {
        return new JamonLexer();
    }

    @NotNull
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        return pack(ATTRIBUTES.get(tokenType));
    }
}
