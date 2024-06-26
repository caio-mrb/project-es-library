package org.example;

import javax.swing.JPanel;

public interface NamedPanel {
    String getPanelName();

    static String getPanelNameFor(Class<? extends NamedPanel> panelClass) {
        try {
            return panelClass.getDeclaredConstructor().newInstance().getPanelName();
        } catch (Exception e) {
            throw new RuntimeException("Error getting panel name", e);
        }
    }


}
