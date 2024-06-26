package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class MainBookPanel extends BasePanel implements FillablePanel {
    private JPanel mainBookPanel;
    private JButton showMostRequestedBooksBtn;
    private JButton returnBtn;
    private JPanel fieldsPanel;
    private JPanel searchPanel;
    private JLabel nameSearchLbl;
    private JTextField nameSearchTxtInput;
    private JButton searchBtn;
    private JPanel bookInfoPanel;
    private JPanel line1Panel;
    private JLabel nameLbl;
    private JTextField nameTxtInput;
    private JLabel editionLbl;
    private JTextField editionTxtInput;
    private JPanel line2Panel;
    private JLabel genreLbl;
    private JLabel subGenreLbl;
    private JLabel supplierLbl;
    private JComboBox<String> genreComboBox;
    private JComboBox<String> subGenreComboBox;
    private JComboBox<Supplier> supplierComboBox;
    private JPanel line3Panel;
    private JCheckBox activeCheckBox;
    private JPanel quantityPanel;
    private JScrollPane quantityScrollPanel;
    private JTable quantityTable;
    private JLabel quantityLbl;
    private JLabel quantityNumLbl;
    private JButton addQuantityBtn;
    private JPanel buttonsPanel;
    private JButton addNewBookBtn;
    private JButton editBtn;
    private JButton saveBtn;
    private JLabel codeLbl;
    private JTextField codeTxtInput;
    private JLabel authorLbl;
    private JTextField authorTxtInput;
    private JLabel codeSearchLbl;
    private JTextField codeSearchTxtInput;
    private Searchable foundBook;

    private String[] quantityBooksColumnNames;
    private String[] quantityBooksData;
    private LinkedList<Book> books;
    private LinkedList<Supplier> suppliers;

    public MainBookPanel(){
        super("MainBookPanel");



    }

    public MainBookPanel(LinkedList<Book> books, LinkedList<String> genres, LinkedList<String> subGenres, LinkedList<Supplier> suppliers, BaseBookPanel baseBookPanel) {
        this();

        quantityBooksColumnNames = new String[]{"Código", "ISBN", "Estante", "Prateleira"};
        quantityScrollPanel.setViewportView(quantityTable);

        this.books = books;
        this.suppliers = suppliers;
        genreComboBox.setModel(new DefaultComboBoxModel<>(genres.toArray(new String[0])));
        subGenreComboBox.setModel(new DefaultComboBoxModel<>(subGenres.toArray(new String[0])));


        genreComboBox.setSelectedIndex(-1);

        subGenreComboBox.setSelectedIndex(-1);

        //mainBookPanel.add(new mainBookPanel().getMainBookPanel(),"mainBookPanel");
        showMostRequestedBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goTo(MostRequestedBookPanel.class,0);
            }
        });

        addNewBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAllButtons();
                clearAllFields();
                enableAllFields();

                updateSupplierComboBox(true);
                activeCheckBox.setEnabled(false);
                activeCheckBox.setSelected(true);
                saveBtn.setEnabled(true);
                addNewBookBtn.setEnabled(false);
                codeTxtInput.setText(String.valueOf(books.size()+1));

            }
        });

        addQuantityBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                baseBookPanel.getAddQuantityBookPanel().setStartup((Book)foundBook);
                goTo(AddQuantityBookPanel.class,0);

            }
        });
        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(saveBtn.isEnabled() && foundBook != null){
                    resetAllButtons();
                    disableAllFields();
                    clearAllFields();
                    display(foundBook);
                    editBtn.setEnabled(true);
                    addQuantityBtn.setEnabled(true);
                    return;
                }
                if (!addNewBookBtn.isEnabled())
                {
                    resetAllButtons();
                    disableAllFields();
                    clearAllFields();
                    return;
                }
                if (nameTxtInput.getText().isEmpty()){
                    goTo(HomePanel.class,2);
                    foundBook = null;
                    clearAllFields();
                    resetAllButtons();
                    return;
                }

                resetAllButtons();
                disableAllFields();
                clearAllFields();

            }
        });

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (codeSearchTxtInput.getText().isEmpty() && nameSearchTxtInput.getText().isEmpty()){
                    clearAllFields();
                    resetAllButtons();
                    return;
                }

                if (codeSearchTxtInput.getText().isEmpty() || !codeSearchTxtInput.getText().matches("\\d+")){
                    foundBook = Searchable.findBy(books, nameSearchTxtInput.getText());
                }

                if (!codeSearchTxtInput.getText().isEmpty() && codeSearchTxtInput.getText().matches("\\d+"))
                {
                    foundBook = Searchable.findBy(books, Integer.parseInt(codeSearchTxtInput.getText()));
                }

                if (foundBook == null){
                    JOptionPane.showMessageDialog(null, "O livro não foi encontrado.",
                            "Exemplar", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                codeSearchTxtInput.setText(String.valueOf(foundBook.getCode()));
                nameSearchTxtInput.setText(foundBook.getName());

                supplierComboBox.setModel(new DefaultComboBoxModel<>(suppliers.toArray(new Supplier[0])));
                display(foundBook);
                addQuantityBtn.setEnabled(true);
                editBtn.setEnabled(true);
            }
        });

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAllButtons();

                updateSupplierComboBox(false);
                supplierComboBox.setModel(new DefaultComboBoxModel<>(suppliers.toArray(new Supplier[0])));
                saveBtn.setEnabled(true);
                addNewBookBtn.setEnabled(false);
                searchBtn.setEnabled(false);
                addQuantityBtn.setEnabled(false);

                enableAllFields();
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(codeTxtInput.getText()) > books.size()){
                    add();
                }else{
                    update(foundBook);
                }

                disableAllFields();
                display(foundBook);
                resetAllButtons();
                editBtn.setEnabled(true);
            }
        });
    }

    private void initializeQuantityTable(LinkedList<QuantityBook> quantityBooks) {
        Object[][] data = new Object[quantityBooks.size()][4];
        for (int i = 0; i < quantityBooks.size(); i++) {
            QuantityBook book = quantityBooks.get(i);
            data[i][0] = book.getCode();
            data[i][1] = book.getIsbn();
            data[i][2] = book.getBookcase();
            data[i][3] = book.getBookshelf();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, quantityBooksColumnNames);
        quantityTable.setModel(tableModel);
    }

    public void resetAllButtons(){
        searchBtn.setEnabled(true);
        addQuantityBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        editBtn.setEnabled(false);
        addNewBookBtn.setEnabled(true);
    }

    @Override
    public void update(Object obj) {
        Book book = (Book) obj;

        Supplier supplier = (Supplier) supplierComboBox.getSelectedItem();

        this.books.remove(book);
        book.getSupplier().removeBook(book);

        book.setName(nameTxtInput.getText());
        book.setAuthor(authorTxtInput.getText());
        book.setEdition(Integer.parseInt(editionTxtInput.getText()));
        book.setGenre((String) genreComboBox.getSelectedItem());
        book.setSubgenre((String) subGenreComboBox.getSelectedItem());
        book.setSupplier(supplier);
        book.getSupplier().addBook(book);
        book.setActive(activeCheckBox.isSelected());

        this.books.add(book);
    }

    @Override
    public void add() {
        Supplier supplier = (Supplier) supplierComboBox.getSelectedItem();
        foundBook = new Book(
                Integer.parseInt(codeTxtInput.getText()),
                nameTxtInput.getText(),
                authorTxtInput.getText(),
                Integer.parseInt(editionTxtInput.getText()),
                (String) genreComboBox.getSelectedItem(),
                (String) subGenreComboBox.getSelectedItem(),
                supplier
        );

       ((Book) foundBook).getSupplier().addBook((Book) foundBook);
        this.books.add((Book) foundBook);

    }

    public void disableAllFields(){
        nameTxtInput.setEditable(false);
        authorTxtInput.setEditable(false);
        editionTxtInput.setEditable(false);
        genreComboBox.setEnabled(false);
        subGenreComboBox.setEnabled(false);
        supplierComboBox.setEnabled(false);
        activeCheckBox.setEnabled(false);
    }

    public void enableAllFields(){
        nameTxtInput.setEditable(true);
        authorTxtInput.setEditable(true);
        editionTxtInput.setEditable(true);
        genreComboBox.setEnabled(true);
        subGenreComboBox.setEnabled(true);
        supplierComboBox.setEnabled(true);
        activeCheckBox.setEnabled(true);
    }

    @Override
    public void display(Object obj) {
        Book book = (Book) obj;
        codeSearchTxtInput.setText(String.valueOf(book.getCode()));
        nameSearchTxtInput.setText(book.getName());
        codeTxtInput.setText(String.valueOf(book.getCode()));
        nameTxtInput.setText(book.getName());
        authorTxtInput.setText(book.getAuthor());
        editionTxtInput.setText(String.valueOf(book.getEdition()));
        genreComboBox.setSelectedItem(book.getGenre());
        subGenreComboBox.setSelectedItem(book.getSubgenre());
        supplierComboBox.setSelectedItem(book.getSupplier());
        activeCheckBox.setSelected(book.getActive());
        initializeQuantityTable(book.getQuantityBooks());
        quantityNumLbl.setText(String.valueOf(book.getQuantityBooks().size()));

    }

    public void clearAllFields(){
        codeSearchTxtInput.setText("");
        nameSearchTxtInput.setText("");
        codeTxtInput.setText("");
        nameTxtInput.setText("");
        authorTxtInput.setText("");
        editionTxtInput.setText("");
        genreComboBox.setSelectedIndex(-1);
        subGenreComboBox.setSelectedIndex(-1);
        supplierComboBox.setSelectedIndex(-1);
        activeCheckBox.setSelected(false);
        DefaultTableModel model = (DefaultTableModel) quantityTable.getModel();
        model.setRowCount(0);
    }

    public void updateSupplierComboBox(boolean resetIndex){
        supplierComboBox.setModel(new DefaultComboBoxModel<>(suppliers.toArray(new Supplier[0])));
        if(resetIndex)
         supplierComboBox.setSelectedIndex(-1);
    }

    @Override
    public JComponent getMainPanel() {
        return mainBookPanel;
    }

}
