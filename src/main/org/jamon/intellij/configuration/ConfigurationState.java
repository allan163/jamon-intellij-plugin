package org.jamon.intellij.configuration;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 9:50 PM
 */
@State(name = "JamonPlugin", storages = {@Storage(id = "other", file="$PROJECT_FILE$")})
public class ConfigurationState implements PersistentStateComponent<ConfigurationState> {
    public String jamonRuntimeJar = "";
    public String jamonApiJar = "";
    public String jamonProcessorJar = "";
    public String destDir = "";

    public ConfigurationState getState() {
        return this;
    }

    public void loadState(ConfigurationState state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
