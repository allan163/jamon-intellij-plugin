package org.jamon.intellij.action;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.roots.ModuleRootModel;
import com.intellij.openapi.vfs.VirtualFile;
import org.jamon.intellij.component.JamonConfig;
import org.jamon.intellij.execution.JamonConsole;
import org.jamon.intellij.execution.JamonExecutor;
import org.jamon.intellij.lang.file.JamonFileType;
import org.jamon.intellij.resource.JamonIconProvider;
import org.jamon.intellij.util.Utils;

/**
 * User: Ryan Brignoni
 * Date: 7/28/11
 * Time: 9:50 PM
 */
public class TranslateAction extends AnAction {
    public TranslateAction() {
        super("", "", JamonIconProvider.JAMON_ICON_24);
    }

    public void actionPerformed(AnActionEvent e) {
        DataContext dataContext = e.getDataContext();
        Project project = DataKeys.PROJECT.getData(dataContext);
        Module module = DataKeys.MODULE.getData(dataContext);
        VirtualFile file = DataKeys.VIRTUAL_FILE.getData(dataContext);
        ModuleRootManager manager = ModuleRootManager.getInstance(module);

        if (file != null && JamonFileType.DEFAULT_EXTENSION.equals(file.getExtension())) {
            VirtualFile srcDir = getSourcePath(manager, file);
            translateTemplate(project, srcDir, file);
        }
    }

    private VirtualFile getSourcePath(ModuleRootModel manager, VirtualFile file) {
        VirtualFile srcDir = null;
        for (VirtualFile sourcePath : manager.getSourceRoots()) {
            if (file != null && file.getPresentableUrl().contains(sourcePath.getPresentableUrl())) {
                srcDir = sourcePath;
                break;
            }
        }
        return srcDir;
    }

    private void translateTemplate(Project project, VirtualFile srcDir, VirtualFile file) {
        JamonConfig jamonConfig = Utils.getJamonConfig(project, file, srcDir);
        JamonConsole console = new JamonConsole(project);

        if (jamonConfig != null) {
            new JamonExecutor(jamonConfig, console).execute();
        }
    }
}
