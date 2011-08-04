package org.jamon.intellij.ui;

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.jamon.intellij.configuration.ConfigurationState;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 7:42 PM
 */
public class ConfigurationPanel implements Configurable {
    public static final String JAMON_RUNTIME = "jamon-runtime";
    public static final String JAMON_API = "jamon-api";
    public static final String JAMON_PROCESSOR = "jamon-processor";

    private final Project myProject;
    private JPanel rootComponent;
    private TextFieldWithBrowseButton jamonRuntimeJar;
    private TextFieldWithBrowseButton jamonApiJar;
    private TextFieldWithBrowseButton jamonProcessorJar;
    private TextFieldWithBrowseButton destDir;

    public ConfigurationPanel(Project project) {
        myProject = project;
        addJarListener(jamonRuntimeJar, JAMON_RUNTIME);
        addJarListener(jamonApiJar, JAMON_API);
        addJarListener(jamonProcessorJar, JAMON_PROCESSOR);

        destDir.addBrowseFolderListener("Choose Output Destination",
                "Select the directory where you would like to save the compiled jamon templates.",
                myProject, FileChooserDescriptorFactory.createSingleFolderDescriptor());
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
        return null;
    }

    public String getHelpTopic() {
        return null;
    }

    public JComponent createComponent() {
        return rootComponent;
    }

    public void apply() throws ConfigurationException {
        getData(myProject.getComponent(ConfigurationState.class));
    }

    public boolean isModified() {
        return isModified(myProject.getComponent(ConfigurationState.class));
    }
    public void reset() {
        setData(myProject.getComponent(ConfigurationState.class));
    }

    public void disposeUIResources() {
    }

    public void setData(ConfigurationState data) {
        jamonRuntimeJar.setText(data.jamonRuntimeJar);
        jamonApiJar.setText(data.jamonApiJar);
        jamonProcessorJar.setText(data.jamonProcessorJar);
        destDir.setText(data.destDir);
    }

    public void getData(ConfigurationState data) {
        data.jamonRuntimeJar = jamonRuntimeJar.getText();
        data.jamonApiJar = jamonApiJar.getText();
        data.jamonProcessorJar = jamonProcessorJar.getText();
        data.destDir = destDir.getText();
    }

    @SuppressWarnings({"RedundantIfStatement"})
    public boolean isModified(ConfigurationState data) {
        if (jamonRuntimeJar.getText() != null && !jamonRuntimeJar.getText().equals(data.jamonRuntimeJar)) {
            return true;
        }

        if (jamonApiJar.getText() != null && !jamonApiJar.getText().equals(data.jamonApiJar)) {
            return true;
        }

        if (jamonProcessorJar.getText() != null && !jamonProcessorJar.getText().equals(data.jamonProcessorJar)) {
            return true;
        }

        if (destDir.getText() != null && !destDir.getText().equals(data.destDir)) {
            return true;
        }

        return false;
    }
}
