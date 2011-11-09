package org.jamon.intellij.execution;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.JavaParameters;
import com.intellij.execution.configurations.ParametersList;
import com.intellij.execution.process.DefaultJavaProcessHandler;
import com.intellij.execution.process.OSProcessHandler;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.JavaSdk;
import com.intellij.openapi.projectRoots.ProjectJdkTable;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.MessageView;
import org.jamon.intellij.component.JamonConfig;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/7/11
 * Time: 10:45 PM
 */
public class JamonExecutor {
    private static final String JAMON_MAIN_CLASS = "org.jamon.compiler.TemplateProcessor";
    private static final Key<JamonExecutor> CONSOLE_KEY = Key.create("MAVEN_CONSOLE_KEY");
    public static final String CONSOLE_TITLE = "Jamon";

    private final AtomicBoolean isOpen = new AtomicBoolean(false);
    private final Project myProject;
    private final JamonConfig config;
    private final ConsoleView console;

    private OSProcessHandler processHandler;

    public JamonExecutor(Project project, JamonConfig jamonConfig, ConsoleView consoleView) {
        this.myProject = project;
        this.config = jamonConfig;
        this.console = consoleView;
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
            processHandler = new DefaultJavaProcessHandler(createJavaParameters()) {
                @Override
                public void notifyTextAvailable(String text, Key outputType) {
                    super.notifyTextAvailable(text, outputType);
                    ensureAttachedToToolWindow();
                }
            };
            console.attachToProcess(processHandler);
        } catch (ExecutionException e) {
            ensureAttachedToToolWindow();
            console.print(e.getMessage(), ConsoleViewContentType.ERROR_OUTPUT);
        }

        processHandler.addProcessListener(new ProcessAdapter() {
            @Override
            public void processTerminated(ProcessEvent event) {
                // Refresh the VFS to pick up the newly translated Proxy/Impl classes.
                VirtualFileManager.getInstance().refresh(true);
            }
        });

        processHandler.startNotify();
        processHandler.waitFor();

        if (processHandler != null) {
            processHandler.destroyProcess();
            processHandler.waitFor();
        }
    }

    private static void invokeLater(final Project p, final Runnable r) {
        final ModalityState state = ModalityState.defaultModalityState();
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                if (p.isDisposed()) return;
                r.run();
            }
        }, state);
    }

    private void ensureAttachedToToolWindow() {
        if (!isOpen.compareAndSet(false, true)) return;
        invokeLater(myProject, new Runnable() {
            public void run() {
                MessageView messageView = MessageView.SERVICE.getInstance(myProject);

                Content content = ContentFactory.SERVICE.getInstance().createContent(
                        console.getComponent(), "Jamon", true);
                content.putUserData(CONSOLE_KEY, JamonExecutor.this);
                messageView.getContentManager().addContent(content);
                messageView.getContentManager().setSelectedContent(content);

                // remove unused tabs
                for (Content each : messageView.getContentManager().getContents()) {
                    if (each.isPinned()) continue;
                    if (each == content) continue;

                    JamonExecutor console = each.getUserData(CONSOLE_KEY);
                    if (console == null) continue;

                    if (!CONSOLE_TITLE.equals(console.getTitle())) continue;

    //                if (console.isFinished()) {
    //                    messageView.getContentManager().removeContent(each, false);
    //                }
                }

                ToolWindow toolWindow = ToolWindowManager.getInstance(myProject).getToolWindow(ToolWindowId.MESSAGES_WINDOW);
                if (!toolWindow.isActive()) {
                    toolWindow.activate(null, false);
                }
            }
        });
    }

    public String getTitle() {
        return CONSOLE_TITLE;
    }
}
