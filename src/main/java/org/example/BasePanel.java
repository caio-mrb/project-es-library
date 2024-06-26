package org.example;

import javax.swing.*;
import java.awt.*;

public abstract class BasePanel extends JPanel implements NamedPanel {
    private final String name;

    public BasePanel(String name) {
        this.name = name;
    }

    @Override
    public String getPanelName() {
        return name;
    }

    public void goTo(Class<? extends NamedPanel> panelClass, int deep){
        JPanel parent = (JPanel)this.getMainPanel().getParent();
        for (int i = 0; i < deep; i++) {
            parent = (JPanel)parent.getParent();
        }
        CardLayout layout = (CardLayout)parent.getLayout();
        layout.show(parent, NamedPanel.getPanelNameFor(panelClass));
    }

    public abstract JComponent getMainPanel();

}