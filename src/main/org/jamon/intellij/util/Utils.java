package org.jamon.intellij.util;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.components.ComponentManager;

public class Utils {
    public static void invokeLater(final ComponentManager p, final Runnable r) {
        final ModalityState state = ModalityState.defaultModalityState();
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            public void run() {
                if (!p.isDisposed()) {
                    r.run();
                }
            }
        }, state);
    }
}
