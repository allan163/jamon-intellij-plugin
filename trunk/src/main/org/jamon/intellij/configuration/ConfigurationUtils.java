package org.jamon.intellij.configuration;

import com.intellij.openapi.diagnostic.Logger;

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
}
