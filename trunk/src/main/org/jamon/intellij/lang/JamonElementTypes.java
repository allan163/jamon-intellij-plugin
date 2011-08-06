package org.jamon.intellij.lang;

import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:55 PM
 */
public interface JamonElementTypes {
    IFileElementType FILE = new IFileElementType(Language.findInstance(JamonLanguage.class));
    IElementType JAMON_ARGS_START = new JamonElementType("ARGS_START");
    IElementType JAMON_ARGS_CONTENT = new JamonElementType("ARGS_CONTENT");
    IElementType JAMON_ARGS_END = new JamonElementType("ARGS_END");
}
