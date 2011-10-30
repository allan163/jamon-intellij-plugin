package org.jamon.intellij.component;

import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
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
    private static final String TEMPLATE_PROCESSOR_CLASS = "org.jamon.compiler.TemplateProcessor";
    private static final Logger LOGGER = Logger.getInstance(TranslateTemplate.class.getSimpleName());

    public TranslateTemplate(Project project) {
        super(project);
    }

    public void translateFile(VirtualFile file) {
        JamonConfig jamonConfig = Utils.getJamonConfig(myProject, file);
        if (jamonConfig != null) {
            translate(file, jamonConfig);
        }
    }

    private void translate(VirtualFile file, JamonConfig jamonConfig) {
        URLClassLoader classLoader = URLClassLoader.newInstance(jamonConfig.getJamonLibUrls());
        try {
            Class clazz = classLoader.loadClass(TEMPLATE_PROCESSOR_CLASS);
            Constructor c = clazz.getConstructor(File.class, File.class, ClassLoader.class);
            Object processor = c.newInstance(jamonConfig.getDestDir(), jamonConfig.getSrcDir(),
                classLoader);
            Method generateSource = clazz.getMethod("generateSource", String.class);
            generateSource.invoke(processor, file.getName());
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            Messages.showMessageDialog(myProject,
                "Unable to load jamon, please check your settings.",
                "Unable to Translate", Messages.getErrorIcon());
        }
    }
}
