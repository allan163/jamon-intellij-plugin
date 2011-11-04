package org.jamon.intellij.ui;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.module.Module;
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
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/1/11
 * Time: 8:31 PM
 */
public class ModuleConfigurationTab implements Configurable {
    private final Project myProject;
    private final Module myModule;

    private JPanel rootComponent;
    private TextFieldWithBrowseButton outputDirectory;

    public ModuleConfigurationTab(Module module) {
        myProject = module.getProject();
        myModule = module;

        outputDirectory.addBrowseFolderListener("Choose Output Destination",
                "Select the directory where you would like to save the compiled jamon templates.",
                myProject, FileChooserDescriptorFactory.createSingleFolderDescriptor());
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

    public boolean isModified() {
        ConfigurationState configuration = myProject.getComponent(ConfigurationState.class);
        String output = outputDirectory.getText();
        return (!StringUtils.isEmpty(output) && !output.equals(configuration.getOutputDirectory(myModule)));
    }

    public void apply() throws ConfigurationException {
        ConfigurationState configuration = myProject.getComponent(ConfigurationState.class);
        configuration.setOutputDirectory(myModule, outputDirectory.getText());
    }

    public void reset() {
        ConfigurationState configuration = myProject.getComponent(ConfigurationState.class);
        outputDirectory.setText(configuration.getOutputDirectory(myModule));
    }

    public void disposeUIResources() {
    }
}
