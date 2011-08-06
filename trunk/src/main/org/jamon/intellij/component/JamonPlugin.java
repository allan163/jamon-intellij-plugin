package org.jamon.intellij.component;

import com.intellij.lang.Language;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileTypes.FileTypeManager;
import org.jamon.intellij.lang.JamonFileType;
import org.jamon.intellij.lang.JamonLanguage;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 8/3/11
 * Time: 8:31 PM
 */
public class JamonPlugin implements ApplicationComponent {
    public static final JamonFileType JAMON_FILE_TYPE = new JamonFileType();
    public static final Language JAMON_LANGUAGE = new JamonLanguage();

    @NotNull
    public String getComponentName() {
        return "Jamon support loader";
    }

    public void initComponent() {
        ApplicationManager.getApplication().runWriteAction(
                new Runnable() {
                    public void run() {
                        FileTypeManager.getInstance().registerFileType(JAMON_FILE_TYPE, JAMON_FILE_TYPE.EXTENSION);
                    }
                }
        );
    }

    public void disposeComponent() {
    }
}
