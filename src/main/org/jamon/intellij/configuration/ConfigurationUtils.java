package org.jamon.intellij.configuration;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 11:00 PM
 */
public class ConfigurationUtils {
    private static final Logger LOGGER = Logger.getInstance(ConfigurationUtils.class.getSimpleName());

    public static File[] getJamonFiles(final ConfigurationState state) {
        return convertPathsToFileArray(state.jamonRuntimeJar, state.jamonApiJar, state.jamonProcessorJar);
    }

    private static File[] convertPathsToFileArray(String... paths) {
        File[] files = new File[paths.length];
        for (int i = 0; i < paths.length; i++) {
            try {
                files[i] = new File(paths[i]);
            } catch (Exception e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }
        return files;
    }

    public static JamonConfig getJamonConfig(Project project, VirtualFile srcDir, VirtualFile template) {
        ConfigurationState state = project.getComponent(ConfigurationState.class);

        File[] jamonFiles = getJamonFiles(state);

        for (File jarFile : jamonFiles) {
            if (!jarFile.exists()) {
                Messages.showMessageDialog(project,
                        "It appears that the Jamon plugin has not been properly configured yet.",
                        "Jamon Not Found", Messages.getErrorIcon());
                return null;
            }
        }

        Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(template);
        File destDir = new File(state.getOutputDirectory(module));
        destDir.mkdirs();

        if (state.getOutputDirectories().isEmpty() || !destDir.exists() || !destDir.isDirectory()) {
            Messages.showMessageDialog(project,
                    "It appears that the Jamon plugin has not been properly configured yet.",
                    "No Output Directory", Messages.getErrorIcon());
            return null;
        }

        return new JamonConfig(jamonFiles, srcDir, destDir, template);
    }

    public static boolean shouldCompileSources(Project project) {
        ConfigurationState state = project.getComponent(ConfigurationState.class);
        return state.compileSources;
    }
}
