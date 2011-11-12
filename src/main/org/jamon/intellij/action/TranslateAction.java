package org.jamon.intellij.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VirtualFile;
import org.jamon.intellij.configuration.ConfigurationUtils;
import org.jamon.intellij.configuration.JamonConfig;
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
        VirtualFile template = DataKeys.VIRTUAL_FILE.getData(dataContext);
        ModuleRootManager manager = ModuleRootManager.getInstance(module);

        if (template != null && JamonFileType.DEFAULT_EXTENSION.equals(template.getExtension())) {
            VirtualFile srcDir = Utils.getSourcePath(manager, template);
            translateTemplate(project, srcDir, template);
        }
    }

    private void translateTemplate(Project project, VirtualFile srcDir, VirtualFile template) {
        JamonConfig jamonConfig = ConfigurationUtils.getJamonConfig(project, srcDir, template);
        JamonConsole console = new JamonConsole(project);

        if (jamonConfig != null) {
            new JamonExecutor(project, jamonConfig, console).execute();
        }
    }

    @Override
    public void update(AnActionEvent e) {
        DataContext dataContext = e.getDataContext();
        Project project = DataKeys.PROJECT.getData(dataContext);
        if (project != null) {
            FileEditorManager editorManager = FileEditorManager.getInstance(project);
            Editor editor = null;

            if (editorManager != null) {
                editor = editorManager.getSelectedTextEditor();
            }

            if (editor != null) {
                VirtualFile file = FileDocumentManager.getInstance().getFile(editor.getDocument());
                if (JamonFileType.DEFAULT_EXTENSION.equals(file.getExtension())) {
                    e.getPresentation().setEnabled(true);
                    return;
                }

            }
        }

        e.getPresentation().setEnabled(false);
    }
}
