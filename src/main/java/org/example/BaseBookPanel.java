package org.example;

import javax.swing.*;
import java.util.LinkedList;

public class BaseBookPanel extends BasePanel {
    private JPanel basePanel;
    private JPanel titlePanel;
    AddQuantityBookPanel addQuantityBookPanel;
    MainBookPanel mainBookPanel;

    public BaseBookPanel() {
        super("BaseBookPanel");

    }

    public BaseBookPanel(LinkedList<Book> books, LinkedList<String> genres, LinkedList<String> subGenres, LinkedList<Supplier> suppliers) {
        this();

        this.addQuantityBookPanel = new AddQuantityBookPanel(this);
        this.mainBookPanel = new MainBookPanel(books, genres, subGenres, suppliers,this);

        titlePanel.add(mainBookPanel.getMainPanel(), NamedPanel.getPanelNameFor(MainBookPanel.class));
        titlePanel.add(new MostRequestedBookPanel().getMainPanel(), NamedPanel.getPanelNameFor(MostRequestedBookPanel.class));
        titlePanel.add(addQuantityBookPanel.getMainPanel(), NamedPanel.getPanelNameFor(AddQuantityBookPanel.class));
    }

    public AddQuantityBookPanel getAddQuantityBookPanel() {
        return addQuantityBookPanel;
    }

    public MainBookPanel getMainBookPanel() {
        return mainBookPanel;
    }

    @Override
    public JComponent getMainPanel() {
        return basePanel;
    }
}
