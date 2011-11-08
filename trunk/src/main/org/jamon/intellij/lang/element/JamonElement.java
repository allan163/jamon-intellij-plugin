package org.jamon.intellij.lang.element;

import com.intellij.psi.tree.IElementType;
import org.jamon.intellij.lang.file.JamonFileType;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/5/11
 * Time: 8:54 PM
 */
public class JamonElement extends IElementType {
    private String rawContent = "";
    public JamonElement(String debugName) {
        super(debugName, JamonFileType.JAMON_LANGUAGE);
    }

    public JamonElement setRawContent(String value) {
        this.rawContent = value;
        return this;
    }

    public String getRawContent() {
        return this.rawContent;
    }
}
