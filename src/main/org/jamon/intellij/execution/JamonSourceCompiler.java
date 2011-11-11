package org.jamon.intellij.execution;

import com.intellij.openapi.compiler.CompilerManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.jamon.intellij.configuration.JamonConfig;
import org.jamon.intellij.util.Utils;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: Ryan Brignoni
 * Date: 11/10/11
 * Time: 10:49 PM
 */
public class JamonSourceCompiler implements Runnable {
    public static final String PROXY_EXTENSION = ".java";
    public static final String IMPL_EXTENSION = "Impl.java";

    private final Project project;
    private final JamonConfig config;

    public JamonSourceCompiler(Project project, JamonConfig config) {
        this.project = project;
        this.config = config;
    }

    private void compileTemplate() {
        CompilerManager manager = CompilerManager.getInstance(project);

        VirtualFile[] generatedSources = getJavaFilesForTemplate(config);

        if (generatedSources != null) {
            manager.compile(generatedSources, null, true);
        }
    }

    public static VirtualFile[] getJavaFilesForTemplate(JamonConfig config) {
        File proxy = new File(getGeneratedTemplate(config, PROXY_EXTENSION));
        File impl = new File(getGeneratedTemplate(config, IMPL_EXTENSION));

        if (proxy.exists() && impl.exists()) {
            VirtualFile[] generatedSources = new VirtualFile[2];
            generatedSources[0] = LocalFileSystem.getInstance().findFileByIoFile(proxy);
            generatedSources[1] = LocalFileSystem.getInstance().findFileByIoFile(impl);

            return generatedSources;
        }

        return null;
    }

    private static String getGeneratedTemplate(JamonConfig config, String extension) {
        VirtualFile template = config.getTemplate();
        return config.getDestDir().getAbsolutePath()
                + Utils.getRelativePathForFile(config.getSrcDir(), template.getParent())
                + File.separator
                + template.getNameWithoutExtension() + extension;
    }

    public void run() {
        compileTemplate();
    }
}
