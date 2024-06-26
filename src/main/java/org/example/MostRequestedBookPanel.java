package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MostRequestedBookPanel extends BasePanel{
    private JPanel mostRequestedPanel;
    private JPanel titlePanel;
    private JScrollPane mostRequestedScrollPanel;
    private JTable table1;
    private JButton returnBtn;

    public MostRequestedBookPanel() {
        super("MostRequestedBookPanel");
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               goTo(MainBookPanel.class,0);
            }
        });
    }

    @Override
    public JComponent getMainPanel() {
        return mostRequestedPanel;
    }

}
