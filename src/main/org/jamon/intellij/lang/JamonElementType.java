package org.jamon.intellij.lang;

import com.intellij.psi.tree.IElementType;
import org.jamon.intellij.component.JamonPlugin;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 8:54 PM
 */
public class JamonElementType extends IElementType {
    public JamonElementType(String debugName) {
        super(debugName, JamonPlugin.JAMON.getLanguage());
    }
}
