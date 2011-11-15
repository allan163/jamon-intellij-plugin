package org.jamon.intellij.action;

import com.intellij.ide.actions.CreateFileAction;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import org.jamon.intellij.lang.file.JamonFileType;
import org.jamon.intellij.resource.JamonIconProvider;
import org.jetbrains.annotations.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/3/11
 * Time: 8:51 PM
 */
public class NewJamonTemplateAction extends CreateFileAction {
    public static final String ACTION_NAME = "Create New Jamon Template";

    protected NewJamonTemplateAction() {
        super(ACTION_NAME, ACTION_NAME, JamonIconProvider.JAMON_ICON_16);
    }

    @NotNull
    @Override
    protected PsiElement[] create(String newName, PsiDirectory directory) throws Exception {
        Project project = directory.getProject();
        PsiFile file = PsiFileFactory.getInstance(project).createFileFromText(
                newName, JamonFileType.JAMON_FILE_TYPE, "");
        return new PsiElement[]{directory.add(file)};
    }

    @NotNull
    @Override
    protected PsiElement[] invokeDialog(Project project, PsiDirectory directory) {
        JamonValidator validator = new JamonValidator(project, directory);
        Messages.showInputDialog(project, "Enter new Jamon Template Name",
                "New Jamon Template", Messages.getQuestionIcon(), "template.jamon", validator);
        return validator.getCreatedElements();
    }

    @Override
    public boolean isAvailable(DataContext dataContext) {
        Project project = PlatformDataKeys.PROJECT.getData(dataContext);
        VirtualFile file = PlatformDataKeys.VIRTUAL_FILE.getData(dataContext);

        if (!super.isAvailable(dataContext)) {
            return false;
        }

        if (file == null) {
            return false;
        }

        if (!file.isDirectory() || !file.isWritable()) {
            return false;
        }

        assert project != null;
        PsiDirectory directory = PsiManager.getInstance(project).findDirectory(file);

        return directory != null
                && JavaDirectoryService.getInstance().getPackage(directory) != null;

    }

    private final class JamonValidator extends MyValidator {
        public JamonValidator(Project project, PsiDirectory directory) {
            super(project, directory);
        }

        @Override
        public boolean checkInput(String inputString) {
            return inputString.endsWith(JamonFileType.DEFAULT_EXTENSION);
        }
    }
}
