package org.jamon.intellij.lang.element;

import com.intellij.psi.impl.source.tree.LazyParseablePsiElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/17/11
 * Time: 12:51 AM
 */
public class OutputElementImpl extends LazyParseablePsiElement {
    public OutputElementImpl(CharSequence buffer) {
        super(JamonElementTypes.OUTPUT, buffer);
    }
}
