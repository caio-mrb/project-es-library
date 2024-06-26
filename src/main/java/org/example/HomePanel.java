package org.example;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class HomePanel extends BasePanel {
    private JPanel homePanel;
    private JButton partnersBtn;
    private JButton booksBtn;
    private JButton suppliersBtn;
    private JPanel topPanel;
    private JPanel botPanel;
    private JLabel titleTxt;
    private JLabel subtitleTxt;
    private static final String name = "HomePanel";

    public HomePanel() {
        super("HomePanel");

        partnersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goTo(PartnerPanel.class,0);
            }
        });

        booksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goTo(BaseBookPanel.class,0  );
            }
        });

        suppliersBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goTo(SupplierPanel.class,0);
            }
        });
    }

    @Override
    public JComponent getMainPanel() {
        return homePanel;
    }
}
