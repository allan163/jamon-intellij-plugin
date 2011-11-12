package org.jamon.intellij.action;

import com.intellij.ide.util.PackageUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import org.jamon.intellij.configuration.ConfigurationUtils;
import org.jamon.intellij.configuration.JamonConfig;
import org.jamon.intellij.resource.JamonIconProvider;
import org.jamon.intellij.util.Utils;

import java.io.File;

public class JumpToImplAction extends AnAction{

    public JumpToImplAction() {
        super("", "", JamonIconProvider.JAMON_ICON_24);
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
      DataContext dataContext = e.getDataContext();
      Project project = DataKeys.PROJECT.getData(dataContext);

      if (project != null) {
          VirtualFile file = DataKeys.VIRTUAL_FILE.getData(dataContext);
        if (file != null) {
            PsiFile psiFile = e.getData(LangDataKeys.PSI_FILE);
            Module module = DataKeys.MODULE.getData(dataContext);
            ModuleRootManager manager = ModuleRootManager.getInstance(module);
            VirtualFile srcDir = Utils.getSourcePath(manager, file);
            JamonConfig jamonConfig = ConfigurationUtils.getJamonConfig(project, srcDir, file);
            String filePath = getFilePathToOpen(manager, psiFile, file, jamonConfig);
            openFile(project, filePath);
        }
      }
    }

    private void openFile(Project project, String filePath) {
        VirtualFile fileToOpen = LocalFileSystem.getInstance().findFileByPath(filePath);
        if (fileToOpen != null) {
            FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            fileEditorManager.openFile(fileToOpen, true);
        }
    }

    private String getFilePathToOpen(ModuleRootManager manager, PsiFile psiFile, VirtualFile file,
                                     JamonConfig jamonConfig) {
        String extension = file.getExtension();
        if (extension != null) {
            if (extension.contains("jamon")) {
                return getImplFileName(file, jamonConfig);
            }
            else if (extension.contains("java")) {
                return getJamonFileName(manager, psiFile, file, jamonConfig);
            }
        }
        return null;
    }

    private String getJamonFileName(ModuleRootManager manager, PsiFile psiFile, VirtualFile file, JamonConfig jamonConfig) {
        if (psiFile instanceof PsiJavaFile) {
            PsiJavaFile psiJavaFile = (PsiJavaFile) psiFile;
            String packageName = psiJavaFile.getPackageName();
            String packagePath = packageName.replace('.', File.separatorChar);
            String jamonSourcePath = packagePath + File.separator + file.getNameWithoutExtension() + ".jamon";
            return findInModuleSources(manager, jamonSourcePath);
        }
        return null;
    }

    private String findInModuleSources(ModuleRootManager manager, String relativePathFromSource) {
        for (VirtualFile sourcePath : manager.getSourceRoots()) {
            String pathToFind = sourcePath.getPresentableUrl() + File.separator + relativePathFromSource;
            VirtualFile file = LocalFileSystem.getInstance().findFileByPath(pathToFind);
            if (file != null) {
                return file.getPresentableUrl();
            }
        }
        return null;
    }

    private String getImplFileName(VirtualFile file, JamonConfig jamonConfig) {
        String implFileName = getRelativePathWithoutExtension(jamonConfig, file) + ".java";
        return jamonConfig.getDestDir() + File.separator + implFileName;
    }

    private String getRelativePathWithoutExtension(JamonConfig jamonConfig, VirtualFile file) {
        String relativePath = file.getParent().getPresentableUrl() +
            File.separator + file.getNameWithoutExtension();
        relativePath = relativePath.replace(jamonConfig.getSrcDir().getPresentableUrl(), "");
        return relativePath;
    }
}
