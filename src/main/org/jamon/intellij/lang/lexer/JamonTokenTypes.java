package org.jamon.intellij.lang.lexer;

import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jamon.intellij.lang.element.JamonElement;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 11:45 PM
 */
public interface JamonTokenTypes {
    IElementType WHITE_SPACE = TokenType.WHITE_SPACE;
    IElementType BAD_CHARACTER = TokenType.BAD_CHARACTER;
    IElementType COMMENT = new JamonElement("COMMENT");
    IElementType TAG_START_PERCENT = new JamonElement("TAG_START_PERCENT");     // <%
    IElementType TAG_END_PERCENT = new JamonElement("TAG_END_PERCENT");         // %>
    IElementType TAG_CLOSE_PERCENT = new JamonElement("TAG_CLOSE_PERCENT");     // </%
    IElementType GENERIC_TAG_END = new JamonElement("GENERIC_TAG_END");         // >
}
