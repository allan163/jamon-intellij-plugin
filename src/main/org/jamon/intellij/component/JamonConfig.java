package org.jamon.intellij.component;

import java.io.File;
import java.net.URL;

public class JamonConfig {
    private final File[] jamonLibFiles;
    private final String templateName;
    private final File srcDir;
    private final File destDir;

    public JamonConfig(File[] jamonLibFiles,  File srcDir, File destDir, String target) {
        this.jamonLibFiles = jamonLibFiles;
        this.templateName = target;
        this.srcDir = srcDir;
        this.destDir = destDir;
    }

    public File getSrcDir() {
        return srcDir;
    }

    public File getDestDir() {
        return destDir;
    }

    public File[] getJamonLibFiles() {
        return jamonLibFiles;
    }

    public String getTemplateName() {
        return templateName;
    }
}
