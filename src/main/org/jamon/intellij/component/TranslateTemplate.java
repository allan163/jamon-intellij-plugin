package org.jamon.intellij.component;

import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jamon.api.TemplateParser;
import org.jamon.intellij.configuration.ConfigurationState;
import org.jamon.intellij.configuration.ConfigurationUtils;
import org.jamon.intellij.proxy.TemplateProcessorHandler;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 10:39 PM
 */
public class TranslateTemplate extends AbstractProjectComponent {
    private static final String TEMPLATE_PROCESSOR_CLASS = "org.jamon.compiler.TemplateProcessor";
    private static final Logger logger = Logger.getInstance(TranslateTemplate.class.getSimpleName());

    public TranslateTemplate(Project project) {
        super(project);
    }

    public void translateFile(VirtualFile file) {
        ConfigurationState state = myProject.getComponent(ConfigurationState.class);

        URL[] urls = ConfigurationUtils.getJamonUrls(state);

        for (URL url : urls) {
            if (url.getFile().isEmpty()) {
                Messages.showMessageDialog(myProject,
                        "It appears that the Jamon plugin has not been properly configured yet.",
                        "Jamon Not Found", Messages.getErrorIcon());
                return;
            }
        }

        File srcDir = new File(file.getParent().getPresentableUrl());
        File destDir = new File(state.destDir);
        destDir.mkdirs();

        if (state.destDir.isEmpty() || !destDir.exists() || !destDir.isDirectory()) {
            Messages.showMessageDialog(myProject,
                    "It appears that the Jamon plugin has not been properly configured yet.",
                    "No Output Directory", Messages.getErrorIcon());
                return;
        }

//        FileEditorManager.getInstance(myProject).getSelectedTextEditor().getDocument();

        URLClassLoader classLoader = URLClassLoader.newInstance(urls);
        try {
            InvocationHandler handler = new TemplateProcessorHandler();
            TemplateParser parser = (TemplateParser) Proxy.newProxyInstance(TemplateParser.class.getClassLoader(), new Class[] { TemplateParser.class }, handler);
            parser.parseTemplate("test");

            Class clazz = classLoader.loadClass(TEMPLATE_PROCESSOR_CLASS);
            Constructor c = clazz.getConstructor(File.class, File.class, ClassLoader.class);
            Object processor = c.newInstance(destDir, srcDir, classLoader);
            Method generateSource = clazz.getMethod("generateSource", String.class);
            generateSource.invoke(processor, file.getName());
            boolean test = processor != null;
//            logger.warn("" + test);
//            t.generateSource(file.getName());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            Messages.showMessageDialog(myProject,
                    "Unable to load jamon, please check your settings.",
                    "Unable to Translate", Messages.getErrorIcon());
        }
    }
}
