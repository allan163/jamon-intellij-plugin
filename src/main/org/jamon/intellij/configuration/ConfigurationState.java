package org.jamon.intellij.configuration;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.module.Module;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.intellij.util.xmlb.annotations.MapAnnotation;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 9:50 PM
 */
@State(name = "JamonPlugin", storages = {@Storage(id = "other", file="$PROJECT_FILE$")})
public class ConfigurationState implements PersistentStateComponent<ConfigurationState> {
    public static final String DEFAULT_OUTPUT_FOLDER = "generated-src";

    public String jamonRuntimeJar = "";
    public String jamonApiJar = "";
    public String jamonProcessorJar = "";

    @Tag("outputDirectories")
    private Map<String, String> outputDirectories = new HashMap<String, String>();

    public String getOutputDirectory(Module module) {
        if (module != null) {
            return outputDirectories.get(module.getName());
        }
        return null;
    }

    @Property()
    @MapAnnotation()
    public Map<String, String> getOutputDirectories() {
        return outputDirectories;
    }

    public void setOutputDirectories(Map<String, String> directories) {
        outputDirectories = directories;
    }

    public void setOutputDirectory(Module module, String outputDir) {
        outputDirectories.put(module.getName(), StringUtils.isEmpty(outputDir) ? DEFAULT_OUTPUT_FOLDER : outputDir);
    }

    public ConfigurationState getState() {
        return this;
    }

    public void loadState(ConfigurationState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
