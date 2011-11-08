package org.jamon.intellij.lang.element;

import com.intellij.psi.tree.IElementType;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 10:55 PM
 */
public interface JamonElementTypes {
    IElementType IMPORT_START = new JamonElement("IMPORT_START");
    IElementType IMPORT_END = new JamonElement("IMPORT_END");
    IElementType ARGS_CONTENT = new JamonElement("ARGS_CONTENT");
    IElementType ARGS_START = new JamonElement("ARGS_START");
    IElementType ARGS_END = new JamonElement("ARGS_END");
    IElementType EXTEND = new JamonElement("EXTEND");
    IElementType ANNOTATE = new JamonElement("ANNOTATE");
    IElementType CONTENT = new JamonElement("CONTENT");
    IElementType JAVA = new JamonElement("JAVA");
}
