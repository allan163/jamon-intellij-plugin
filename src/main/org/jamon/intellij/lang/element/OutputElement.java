package org.jamon.intellij.lang.element;

import com.intellij.psi.templateLanguages.TemplateLanguage;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/16/11
 * Time: 10:19 PM
 */
public class OutputElement extends JamonElement implements TemplateLanguage {
    public OutputElement(String debugName) {
        super(debugName);
    }
}
