package org.jamon.intellij.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.apache.commons.lang.StringUtils;
import org.jamon.intellij.configuration.ConfigurationState;
import org.jamon.intellij.resource.JamonIconProvider;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 7:42 PM
 */
public class ProjectConfigurationPanel implements Configurable {
    public static final String JAMON_RUNTIME = "jamon-runtime";
    public static final String JAMON_API = "jamon-api";
    public static final String JAMON_PROCESSOR = "jamon-processor";

    private final Project myProject;
    private JPanel rootComponent;
    private TextFieldWithBrowseButton jamonRuntimeJar;
    private TextFieldWithBrowseButton jamonApiJar;
    private TextFieldWithBrowseButton jamonProcessorJar;
    private JCheckBox compileSourcesCheckbox;

    public ProjectConfigurationPanel(Project project) {
        myProject = project;
        addJarListener(jamonRuntimeJar, JAMON_RUNTIME);
        addJarListener(jamonApiJar, JAMON_API);
        addJarListener(jamonProcessorJar, JAMON_PROCESSOR);
    }

    private void addJarListener(TextFieldWithBrowseButton field, String jarName) {
    field.addBrowseFolderListener("Choose " + jarName + ".jar Location",
            "Select the location of the " + jarName + ".jar file.", myProject, new JamonJarChooserDescriptor(jarName));
    }

    @Nls
    public String getDisplayName() {
        return "Jamon";
    }

    public Icon getIcon() {
        return JamonIconProvider.JAMON_ICON_16;
    }

    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        return rootComponent;
    }

    public void apply() throws ConfigurationException {
        saveConfiguration(myProject.getComponent(ConfigurationState.class));
    }

    public boolean isModified() {
        ConfigurationState configuration = myProject.getComponent(ConfigurationState.class);
        String runtime = jamonRuntimeJar.getText();
        String api = jamonApiJar.getText();
        String processor = jamonProcessorJar.getText();

        if (!StringUtils.isEmpty(runtime) && !runtime.equals(configuration.jamonRuntimeJar)) {
            return true;
        } else if(!StringUtils.isEmpty(api) && !api.equals(configuration.jamonApiJar)) {
            return true;
        } else if (!StringUtils.isEmpty(processor) && !processor.equals(configuration.jamonProcessorJar)) {
            return true;
        } else if (compileSourcesCheckbox.isSelected() != configuration.compileSources) {
            return true;
        }

        return false;
    }
    public void reset() {
        loadConfiguration(myProject.getComponent(ConfigurationState.class));
    }

    public void disposeUIResources() {
    }

    private void loadConfiguration(ConfigurationState configuration) {
        jamonRuntimeJar.setText(configuration.jamonRuntimeJar);
        jamonApiJar.setText(configuration.jamonApiJar);
        jamonProcessorJar.setText(configuration.jamonProcessorJar);
        compileSourcesCheckbox.setSelected(configuration.compileSources);
    }

    private void saveConfiguration(ConfigurationState configuration) {
        configuration.jamonRuntimeJar = jamonRuntimeJar.getText();
        configuration.jamonApiJar = jamonApiJar.getText();
        configuration.jamonProcessorJar = jamonProcessorJar.getText();
        configuration.compileSources = compileSourcesCheckbox.isSelected();
    }
}
