package org.jamon.intellij.component;

import com.intellij.openapi.module.ModuleComponent;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import org.apache.commons.lang.StringUtils;
import org.jamon.intellij.configuration.ConfigurationState;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/1/11
 * Time: 10:52 PM
 */
public class ModuleOutputComponent implements ModuleComponent {
    private final Project myProject;
    private final Module myModule;

    public ModuleOutputComponent(Module module) {
        myProject = module.getProject();
        myModule = module;
    }

    public void moduleAdded() {
        ConfigurationState configuration = myProject.getComponent(ConfigurationState.class);

        if (StringUtils.isEmpty(configuration.getOutputDirectory(myModule))) {
            String outputDir = myModule.getModuleFile().getParent().getPresentableUrl() +
                    File.separator + ConfigurationState.DEFAULT_OUTPUT_FOLDER;

            configuration.setOutputDirectory(myModule, outputDir);
        }
    }

    @NotNull
    public String getComponentName() {
        return "ModuleOutputComponent";
    }

    public void initComponent() {
    }

    public void disposeComponent() {
    }

    public void projectOpened() {
    }

    public void projectClosed() {
    }
}
