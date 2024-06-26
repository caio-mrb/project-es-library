package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.LinkedList;

public class SupplierPanel extends BasePanel implements FillablePanel {
    private JPanel supplierPanel;
    private JPanel mainPanel;
    private JPanel fieldsPanel;
    private JPanel searchPanel;
    private JLabel nameSearchLbl;
    private JTextField nameSearchTxtInput;
    private JButton searchBtn;
    private JPanel buttonsPanel;
    private JButton addNewSupplierBtn;
    private JButton returnButton;
    private JButton editBtn;
    private JButton saveBtn;
    private JPanel supplierInfoPanel;
    private JPanel line1Panel;
    private JLabel nameLbl;
    private JTextField nameTxtInput;
    private JLabel nifLbl;
    private JTextField nifTxtInput;
    private JPanel line3Panel;
    private JLabel phoneLbl;
    private JTextField phoneTxtInput;
    private JLabel emailLbl;
    private JTextField emailTxtInput;
    private JPanel line4Panel;
    private JLabel address1Lbl;
    private JTextField address1TxtInput;
    private JLabel postalCodeLbl;
    private JTextField postalCodeTxtInput;
    private JPanel line5Panel;
    private JLabel address2Lbl;
    private JTextField address2TxtInput;
    private JPanel line2Panel;
    private JLabel activeLbl;
    private JCheckBox activeCheckBox;
    private JPanel booksPanel;
    private JList<Book> booksList;
    private JTextField codeTxtInput;
    private JLabel codeLbl;
    private JLabel codeSearchLbl;
    private JTextField codeSearchTxtInput;
    private JScrollPane booksScrollPanel;

    private Searchable foundSupplier;
    private LinkedList<Supplier> suppliers;
    private LinkedList<Book> books;
    private DefaultListModel<Book> listModel;

    public SupplierPanel() {
        super("SupplierPanel");


    }

    public SupplierPanel(LinkedList<Book> books, LinkedList<Supplier> suppliers) {
        this();

        this.books = books;
        this.suppliers = suppliers;
        this.listModel = new DefaultListModel<>();

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (codeSearchTxtInput.getText().isEmpty() && nameSearchTxtInput.getText().isEmpty()){
                    clearAllFields();
                    resetAllButtons();
                    return;
                }

                if (codeSearchTxtInput.getText().isEmpty() || !codeSearchTxtInput.getText().matches("\\d+")){
                    foundSupplier = Searchable.findBy(suppliers, nameSearchTxtInput.getText());
                }

                if (!codeSearchTxtInput.getText().isEmpty() && codeSearchTxtInput.getText().matches("\\d+"))
                {
                    foundSupplier = Searchable.findBy(suppliers, Integer.parseInt(codeSearchTxtInput.getText()));
                }

                if (foundSupplier == null){
                    JOptionPane.showMessageDialog(null, "O fornecedor não foi encontrado.",
                            "Fornecedor", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                codeSearchTxtInput.setText(String.valueOf(foundSupplier.getCode()));
                nameSearchTxtInput.setText(foundSupplier.getName());

                clearAllFields();
                display(foundSupplier);
                editBtn.setEnabled(true);
            }
        });

        addNewSupplierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAllButtons();
                clearAllFields();
                enableAllFields();
                activeCheckBox.setEnabled(false);
                activeCheckBox.setSelected(true);
                saveBtn.setEnabled(true);
                addNewSupplierBtn.setEnabled(false);
                codeTxtInput.setText(String.valueOf(suppliers.size()+1));

            }
        });

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAllButtons();
                searchBtn.setEnabled(false);
                addNewSupplierBtn.setEnabled(false);
                saveBtn.setEnabled(true);
                enableAllFields();
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(codeTxtInput.getText()) > suppliers.size()){
                    add();
                }else{
                    update(foundSupplier);
                }

                disableAllFields();
                display(foundSupplier);
                resetAllButtons();
                editBtn.setEnabled(true);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!editBtn.isEnabled() && !saveBtn.isEnabled()){
                    resetAllButtons();
                    clearAllFields();
                    disableAllFields();
                    foundSupplier = null;
                    goTo(HomePanel.class,0);
                }
                if(saveBtn.isEnabled() && foundSupplier != null){
                    resetAllButtons();
                    clearAllFields();
                    disableAllFields();
                    display(foundSupplier);
                    editBtn.setEnabled(true);
                    return;
                }

                resetAllButtons();
                clearAllFields();
                disableAllFields();



            }
        });
    }

    private void initializeBooksList(LinkedList<Book> books) {
        for (Book book : books) {
            listModel.addElement(book);
        }
        booksList.setModel(listModel);
    }

    @Override
    public JComponent getMainPanel() {
        return supplierPanel;
    }

    @Override
    public void display(Object obj) {
        Supplier supplier = (Supplier) obj;
        nameSearchTxtInput.setText(supplier.getName());
        codeSearchTxtInput.setText(String.valueOf(supplier.getCode()));
        nameTxtInput.setText(supplier.getName());
        codeTxtInput.setText(String.valueOf(supplier.getCode()));
        nifTxtInput.setText(String.valueOf(supplier.getNif()));
        activeCheckBox.setSelected(supplier.getActive());
        phoneTxtInput.setText(String.valueOf(supplier.getPhone()));
        emailTxtInput.setText(supplier.getEmail());
        address1TxtInput.setText(supplier.getAddress1());
        postalCodeTxtInput.setText(supplier.getPostalCode());
        address2TxtInput.setText(supplier.getAddress2());
        initializeBooksList(supplier.getBooks());
    }

    @Override
    public void clearAllFields() {
        nameSearchTxtInput.setText("");
        codeSearchTxtInput.setText("");
        nameTxtInput.setText("");
        codeTxtInput.setText("");
        nifTxtInput.setText("");
        activeCheckBox.setSelected(false);
        phoneTxtInput.setText("");
        emailTxtInput.setText("");
        address1TxtInput.setText("");
        postalCodeTxtInput.setText("");
        address2TxtInput.setText("");
        listModel.clear();

    }

    @Override
    public void enableAllFields() {
        nameTxtInput.setEditable(true);
        nifTxtInput.setEditable(true);
        activeCheckBox.setEnabled(true);
        phoneTxtInput.setEditable(true);
        emailTxtInput.setEditable(true);
        address1TxtInput.setEditable(true);
        postalCodeTxtInput.setEditable(true);
        address2TxtInput.setEditable(true);
    }

    @Override
    public void disableAllFields() {
        nameTxtInput.setEditable(false);
        nifTxtInput.setEditable(false);
        activeCheckBox.setEnabled(false);
        phoneTxtInput.setEditable(false);
        emailTxtInput.setEditable(false);
        address1TxtInput.setEditable(false);
        postalCodeTxtInput.setEditable(false);
        address2TxtInput.setEditable(false);
    }

    @Override
    public void resetAllButtons() {
        searchBtn.setEnabled(true);
        addNewSupplierBtn.setEnabled(true);
        returnButton.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
    }

    @Override
    public void update(Object obj) {
        Supplier supplier = (Supplier) obj;
        suppliers.remove(supplier);

        supplier.setName(nameTxtInput.getText());
        supplier.setNif(Integer.parseInt(nifTxtInput.getText()));
        supplier.setActive(activeCheckBox.isSelected());
        supplier.setPhone(Integer.parseInt(phoneTxtInput.getText()));
        supplier.setEmail(emailTxtInput.getText());
        supplier.setAddress1(address1TxtInput.getText());
        supplier.setPostalCode(postalCodeTxtInput.getText());
        supplier.setAddress2(address2TxtInput.getText());

        suppliers.add(supplier);
    }

    @Override
    public void add() {
        foundSupplier = new Supplier(
                Integer.parseInt(codeTxtInput.getText()),
                nameTxtInput.getText(),
                Integer.parseInt(nifTxtInput.getText()),
                Integer.parseInt(phoneTxtInput.getText()),
                emailTxtInput.getText(),
                address1TxtInput.getText(),
                postalCodeTxtInput.getText(),
                address2TxtInput.getText()
        );

        suppliers.add((Supplier) foundSupplier);
    }
}
