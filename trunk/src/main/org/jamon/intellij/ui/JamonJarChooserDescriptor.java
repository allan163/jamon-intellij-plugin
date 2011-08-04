package org.jamon.intellij.ui;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.vfs.VirtualFile;

import java.util.regex.Pattern;

/**
 * User: Ryan Brignoni
 * Date: 7/30/11
 * Time: 12:04 AM
 */
public class JamonJarChooserDescriptor extends FileChooserDescriptor {
    private final Pattern jarPattern;

    public JamonJarChooserDescriptor(String jarName) {
        super(true, false, true, true, false, false);
        jarPattern = Pattern.compile(jarName + ".*\\.jar$");
    }

    /**
     * Only allow the user to select this file if the file name matches the expected pattern.
     * @param file user selected
     * @return is file selectable
     */
    @Override
    public boolean isFileSelectable(VirtualFile file) {
        boolean selectable = super.isFileSelectable(file);

        return selectable && jarPattern.matcher(file.getName()).find();
    }
}
