package org.jamon.intellij.component;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.jamon.intellij.lang.JamonFileType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/3/11
 * Time: 8:31 PM
 */
public class JamonPlugin implements ApplicationComponent {
    @NotNull
    public String getComponentName() {
        return "Jamon support loader";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }
}
