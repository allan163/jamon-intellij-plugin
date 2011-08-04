package org.jamon.intellij.resource;

import javax.swing.*;

/**
 * User: Ryan Brignoni
 * Date: 7/29/11
 * Time: 10:24 PM
 */
public class IconResource {
    public static final String TOOLBAR_ICON = "icon-25.png";

    private final Icon toolbarIcon;

    private IconResource() {
        toolbarIcon = new ImageIcon(getClass().getClassLoader().getResource(TOOLBAR_ICON));
    }

    private static class IconResourceHolder {
        private static final IconResource INSTANCE = new IconResource();
    }

    public static IconResource get() {
        return IconResourceHolder.INSTANCE;
    }

    public Icon toolbarIcon() {
        return toolbarIcon;
    }
}
