package org.jamon.intellij.execution;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.execution.process.DefaultJavaProcessHandler;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.vfs.VirtualFile;
import org.jamon.intellij.configuration.JamonConfig;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/7/11
 * Time: 10:45 PM
 */
public class JamonExecutor {
    private static final String JAMON_MAIN_CLASS = "org.jamon.compiler.TemplateProcessor";

    private final JamonConfig config;
    private final JamonConsole console;

    private OSProcessHandler processHandler;

    public JamonExecutor(JamonConfig jamonConfig, JamonConsole console) {
        this.config = jamonConfig;
        this.console = console;
    }

    public JavaParameters createJavaParameters() {
        final JavaParameters params = new JavaParameters();

        params.setWorkingDirectory(config.getSrcDir().getPath());
        params.setJdk(ProjectJdkTable.getInstance().findMostRecentSdkOfType(JavaSdk.getInstance()));

        for (File file : config.getJamonLibFiles()) {
            params.getClassPath().add(file);
        }

        params.setMainClass(JAMON_MAIN_CLASS);

        setJamonArguments(params);

        return params;
    }

    private  void setJamonArguments(JavaParameters params) {
        ParametersList parameters = params.getProgramParametersList();
        parameters.add("--destDir=" + config.getDestDir().getAbsolutePath());
        parameters.add("--srcDir=" + config.getSrcDir().getPresentableUrl());
        parameters.add(getRelativePathForFile(config.getSrcDir(), config.getTemplate()));
    }

    private static String getRelativePathForFile(VirtualFile srcDir, VirtualFile template) {
        String filePath = template.getPresentableUrl();
        filePath = filePath.replace(srcDir.getPresentableUrl(), "");
        return filePath;
    }

    public void execute() {
        try {
            processHandler = new DefaultJavaProcessHandler(createJavaParameters());
            console.attachToProcess(processHandler);
        } catch (ExecutionException e) {
            console.print(e.getMessage(), ConsoleViewContentType.ERROR_OUTPUT);
        }

        processHandler.startNotify();
        processHandler.waitFor();

        if (processHandler != null) {
            processHandler.destroyProcess();
            processHandler.waitFor();
        }
    }
}
