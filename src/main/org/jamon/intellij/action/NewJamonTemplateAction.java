package org.jamon.intellij.action;

import com.intellij.CommonBundle;
import com.intellij.ide.actions.CreateFileFromTemplateDialog;
import com.intellij.ide.actions.CreateTemplateInPackageAction;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.util.IncorrectOperationException;
import org.jamon.intellij.lang.JamonFile;
import org.jamon.intellij.lang.JamonFileType;
import org.jamon.intellij.resource.IconResource;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/3/11
 * Time: 8:51 PM
 */
public class NewJamonTemplateAction extends CreateTemplateInPackageAction<JamonFile> {

    protected NewJamonTemplateAction() {
        super("Jamon Template", "Creates new Jamon Template",
                IconResource.get().toolbarIcon(), true);
//        FileTemplateManager.getInstance().addTemplate("Jamon.jamon",
//                JamonFileType.DEFAULT_EXTENSION);
    }

    @Override
    protected PsiElement getNavigationElement(@NotNull JamonFile
                                                          jamonFile) {
        return jamonFile;
    }

    @NotNull
    @Override
    protected CreateFileFromTemplateDialog.Builder buildDialog(Project project,
                                                               PsiDirectory psiDirectory) {
        final CreateFileFromTemplateDialog.Builder builder = CreateFileFromTemplateDialog.
                createDialog(project, "New Jamon Template");
        builder.addKind("Template", IconResource.get().toolbarIcon(), "Jamon.jamon");
        return builder;
    }

    @Override
    protected JamonFile doCreate(PsiDirectory directory, String templateName,
                                               String templateType)
            throws IncorrectOperationException {

        final String fileName = templateName + "." + JamonFileType.DEFAULT_EXTENSION;

        return (JamonFile)JamonTemplateFactory
                .createFromTemplate(directory, templateName, fileName, templateType);
    }

    @Override
    protected String getActionName(PsiDirectory psiDirectory, String s, String s1) {
        return "Jamon Template";
    }

    @Override
    protected String getErrorTitle() {
        return CommonBundle.getErrorTitle();
    }
}
