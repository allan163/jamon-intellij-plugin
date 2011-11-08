package org.jamon.intellij.component;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jamon.intellij.execution.JamonExecutor;
import org.jamon.intellij.util.Utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URLClassLoader;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 10:39 PM
 */
public class TranslateTemplate extends AbstractProjectComponent {
    public TranslateTemplate(Project project) {
        super(project);
    }

    public void translateFile(final VirtualFile file) {
        JamonConfig jamonConfig = Utils.getJamonConfig(myProject, file);
        ConsoleView console =
                TextConsoleBuilderFactory.getInstance().createBuilder(myProject).getConsole();

        if (jamonConfig != null) {
            new JamonExecutor(myProject, jamonConfig, console).execute();
        }
    }

    @Override
    public void disposeComponent() {

    }
}
