package org.jamon.intellij.execution;

import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.process.ProcessAdapter;
import com.intellij.execution.process.ProcessEvent;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowId;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.MessageView;
import org.jamon.intellij.util.Utils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/10/11
 * Time: 8:16 PM
 */
public class JamonConsole {
    public static final String CONSOLE_TITLE = "Jamon";

    private static final Key<JamonConsole> CONSOLE_KEY = Key.create("JAMON_CONSOLE_KEY");

    private final AtomicBoolean isOpen = new AtomicBoolean(false);
    private final ConsoleView console;
    private final Project myProject;

    private boolean finished = false;

    public JamonConsole(Project project) {
        myProject = project;
        console = createConsoleView(myProject);
    }

    public void print(String text, ConsoleViewContentType type) {
        ensureAttachedToToolWindow();
        console.print(text, type);
    }

    public void attachToProcess(ProcessHandler processHandler) {
        console.attachToProcess(processHandler);
        processHandler.addProcessListener(new ProcessAdapter() {
            @Override
            public void processTerminated(ProcessEvent event) {
                finished = true;
                VirtualFileManager.getInstance().refresh(true);
                // todo: determine success and display a message here?
            }

            @Override
            public void onTextAvailable(ProcessEvent event, Key outputType) {
                ensureAttachedToToolWindow();
            }
        });
    }

    private void ensureAttachedToToolWindow() {
        if (isOpen.compareAndSet(false, true)) {
            Utils.invokeLater(myProject, new ConsoleVisibilityHelper());
        }
    }

    public boolean isFinished() {
        return finished;
    }

    private static ConsoleView createConsoleView(Project project) {
        TextConsoleBuilder builder = TextConsoleBuilderFactory.getInstance().createBuilder(project);
        // todo: add error filters here.

        return builder.getConsole();
    }

    private class ConsoleVisibilityHelper implements Runnable {
        public void run() {
            closeUnusedTabs();

            ToolWindow toolWindow = ToolWindowManager.getInstance(myProject)
                    .getToolWindow(ToolWindowId.MESSAGES_WINDOW);

            if (!toolWindow.isActive()) {
                toolWindow.activate(null, false);
            }
        }

        private void closeUnusedTabs() {
            MessageView messageView = MessageView.SERVICE.getInstance(myProject);

            Content content = ContentFactory.SERVICE.getInstance()
                    .createContent(console.getComponent(), CONSOLE_TITLE, true);
            content.putUserData(CONSOLE_KEY, JamonConsole.this);
            messageView.getContentManager().addContent(content);
            messageView.getContentManager().setSelectedContent(content);

            for (Content tab : messageView.getContentManager().getContents()) {
                if (tab.isPinned()) continue;
                if (tab == content) continue;

                JamonConsole console = tab.getUserData(CONSOLE_KEY);
                if (console == null) continue;

                if (console.isFinished()) {
                    messageView.getContentManager().removeContent(tab, false);
                }
            }
        }
    }
}
