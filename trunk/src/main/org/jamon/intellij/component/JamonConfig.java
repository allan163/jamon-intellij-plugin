package org.jamon.intellij.component;

import java.io.File;
import java.net.URL;

public class JamonConfig {
    private final URL[] jamonLibUrls;
    private final File srcDir;
    private final File destDir;

    public JamonConfig(URL[] jamonLibUrls, File srcDir, File destDir) {
        this.jamonLibUrls = jamonLibUrls;
        this.srcDir = srcDir;
        this.destDir = destDir;
    }

    public URL[] getJamonLibUrls() {
        return jamonLibUrls;
    }

    public File getSrcDir() {
        return srcDir;
    }

    public File getDestDir() {
        return destDir;
    }
}
