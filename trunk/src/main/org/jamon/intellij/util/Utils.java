package org.jamon.intellij.util;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jamon.intellij.component.JamonConfig;
import org.jamon.intellij.configuration.ConfigurationState;
import org.jamon.intellij.configuration.ConfigurationUtils;

import java.io.File;
import java.net.URL;

public class Utils {
    public static JamonConfig getJamonConfig(Project project, VirtualFile file) {
        ConfigurationState state = project.getComponent(ConfigurationState.class);

        URL[] jamonLibUrls = ConfigurationUtils.getJamonUrls(state);

        for (URL url : jamonLibUrls) {
            if (url.getFile().isEmpty()) {
                Messages.showMessageDialog(project,
                    "It appears that the Jamon plugin has not been properly configured yet.",
                    "Jamon Not Found", Messages.getErrorIcon());
                return null;
            }
        }

        File srcDir = new File(file.getParent().getPresentableUrl());
        Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(file);
        File destDir = new File(state.getOutputDirectory(module));
        destDir.mkdirs();

        if (state.getOutputDirectories().isEmpty() || !destDir.exists() || !destDir.isDirectory()) {
            Messages.showMessageDialog(project,
                    "It appears that the Jamon plugin has not been properly configured yet.",
                    "No Output Directory", Messages.getErrorIcon());
            return null;
        }

        return new JamonConfig(jamonLibUrls, srcDir, destDir);
    }
}
