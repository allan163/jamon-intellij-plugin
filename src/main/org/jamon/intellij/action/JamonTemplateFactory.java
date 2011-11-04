package org.jamon.intellij.action;

import com.intellij.ide.fileTemplates.*;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;
import org.jamon.intellij.resource.JamonIconProvider;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/3/11
 * Time: 9:18 PM
 */
public class JamonTemplateFactory implements FileTemplateGroupDescriptorFactory {
    private static Logger LOGGER = Logger.getInstance(JamonTemplateFactory.class.getSimpleName());
    public static final String[] TEMPLATES = {"Jamon.jamon"};
    public static final String TEMPLATE_NAME_PROPERTY = "NAME";

    public FileTemplateGroupDescriptor getFileTemplatesDescriptor() {
        FileTemplateGroupDescriptor group =
                new FileTemplateGroupDescriptor("Jamon", JamonIconProvider.JAMON_ICON_16);
        FileTypeManager manager = FileTypeManager.getInstance();
        for (String template : TEMPLATES) {
            group.addTemplate(new FileTemplateDescriptor(template,
                    manager.getFileTypeByFileName(template).getIcon()));
        }

        return group;
    }

    public static PsiFile createFromTemplate(final PsiDirectory directory,
                                             final String name,
                                             String fileName,
                                             String templateName,
                                             String... parameters) {

        final FileTemplate template = FileTemplateManager.getInstance()
                .getDefaultTemplate(templateName);

        Properties properties = new Properties(FileTemplateManager.getInstance()
                .getDefaultProperties());

        properties.setProperty(TEMPLATE_NAME_PROPERTY, name);
        for (int i = 0; i < parameters.length; i += 2) {
          properties.setProperty(parameters[i], parameters[i + 1]);
        }

        String text;
        try {
            text = template.getText(properties);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load template", e);
        }

        final PsiFileFactory factory = PsiFileFactory.getInstance(directory.getProject());
        final PsiFile file = factory.createFileFromText(fileName, text);

        return (PsiFile)directory.add(file);
    }
}
