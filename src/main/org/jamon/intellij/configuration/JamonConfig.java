package org.jamon.intellij.configuration;

import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;

public class JamonConfig {
    private final File[] jamonLibFiles;
    private final VirtualFile template;
    private final VirtualFile srcDir;
    private final File destDir;

    public JamonConfig(File[] jamonLibFiles, VirtualFile srcDir, File destDir, VirtualFile target) {
        this.jamonLibFiles = jamonLibFiles;
        this.template = target;
        this.srcDir = srcDir;
        this.destDir = destDir;
    }

    public VirtualFile getSrcDir() {
        return srcDir;
    }

    public File getDestDir() {
        return destDir;
    }

    public File[] getJamonLibFiles() {
        return jamonLibFiles;
    }

    public VirtualFile getTemplate() {
        return template;
    }
}
