package org.jamon.intellij.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import org.jamon.intellij.resource.IconResource;
import org.jamon.intellij.component.TranslateTemplate;

/**
 * User: Ryan Brignoni
 * Date: 7/28/11
 * Time: 9:50 PM
 */
public class TranslateAction extends AnAction {
    public TranslateAction() {
        super("", "", IconResource.get().toolbarIcon());
    }

    public void actionPerformed(AnActionEvent e) {
        DataContext dataContext = e.getDataContext();
        Project project = DataKeys.PROJECT.getData(dataContext);

        if (project != null) {
            project.getComponent(TranslateTemplate.class).translateFile(DataKeys.VIRTUAL_FILE.getData(dataContext));
        }
    }
}
