package org.jamon.intellij.lang;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/3/11
 * Time: 8:31 PM
 */
public class JamonPlugin implements ApplicationComponent {
    public static final LanguageFileType JAMON = new JamonFileType();

    @NotNull
    public String getComponentName() {
        return "Jamon support loader";
    }

    public void initComponent() {
        ApplicationManager.getApplication().runWriteAction(
                new Runnable() {
                    public void run() {
                        FileTypeManager.getInstance().registerFileType(JAMON, "jamon");
                    }
                }
        );
    }

    public void disposeComponent() {
    }
}
