package org.jamon.intellij.configuration;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 11:00 PM
 */
public class ConfigurationUtils {
    private static final Logger logger = Logger.getInstance(ConfigurationUtils.class.getSimpleName());

    public static URL[] getJamonUrls(final ConfigurationState state) {
        return convertPathsToURLArray(state.jamonRuntimeJar, state.jamonApiJar, state.jamonProcessorJar);
    }

    private static URL[] convertPathsToURLArray(String... paths) {
        URL[] urlPaths = new URL[paths.length];
        for (int i = 0; i < paths.length; i++) {
            try {
                urlPaths[i] = new URL("file", "", paths[i]);
            } catch (MalformedURLException e) {
                logger.warn(e.getMessage(), e);
            }
        }

        return urlPaths;
    }
}
