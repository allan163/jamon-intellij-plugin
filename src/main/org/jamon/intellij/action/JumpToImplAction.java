package org.jamon.intellij.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
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
            Module module = DataKeys.MODULE.getData(dataContext);
            ModuleRootManager manager = ModuleRootManager.getInstance(module);
            VirtualFile srcDir = Utils.getSourcePath(manager, file);
            JamonConfig jamonConfig = ConfigurationUtils.getJamonConfig(project, file, srcDir);
            String filePath = getFilePathToOpen(file, jamonConfig);
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

    private String getFilePathToOpen(VirtualFile file, JamonConfig jamonConfig) {
        String extension = file.getExtension();
        if (extension != null) {
            if (extension.contains("jamon")) {
                return getImplFileName(file, jamonConfig);
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
