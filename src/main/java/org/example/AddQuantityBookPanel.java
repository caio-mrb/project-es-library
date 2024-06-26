package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class AddQuantityBookPanel extends BasePanel {
    private JPanel addQuantityBookPanel;
    private JPanel line1Panel;
    private JPanel bookInfoPanel;
    private JPanel line2Panel;
    private JPanel line3Panel;
    private JPanel buttonsPanel;
    private JButton returnBtn;
    private JButton saveBtn;
    private JTextField nameTxtInput;
    private JTextField codeTxtField;
    private JTextField isbnTxtInput;
    private JTextField bookcaseTxtInput;
    private JTextField bookshelfTxtInput;
    private JLabel nameLbl;
    private JLabel codeLbl;
    private JLabel isbnLbl;
    private JLabel bookcaseLbl;
    private JLabel bookshelfLbl;

    private Book book;

    public AddQuantityBookPanel() {
        super("addQuantityBookPanel");

    }

    public AddQuantityBookPanel(BaseBookPanel baseBookPanel) {
        this();

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllFields();
                goTo(MainBookPanel.class,0);
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<QuantityBook> quantityBooks = book.getQuantityBooks();
                QuantityBook quantityBook =  new QuantityBook(Integer.parseInt(codeTxtField.getText()),
                        isbnTxtInput.getText(),bookcaseTxtInput.getText(),bookshelfTxtInput.getText());
                quantityBooks.add(quantityBook);
                book.setQuantityBooks(quantityBooks);
                baseBookPanel.getMainBookPanel().display(book);
                clearAllFields();
                goTo(MainBookPanel.class,0);
            }
        });
    }

    public void setStartup(Book book) {
        this.setBook(book);
        this.setCode();
    }

    private void setBook(Book book) {
        this.book = book;
        nameTxtInput.setText(book.getName());
    }

    private void setCode() {
        int bookCode = this.book.getCode();
        int lastQuantityBookCode = this.book.getQuantityBooks().size();

        codeTxtField.setText(bookCode + "" + (lastQuantityBookCode+1));
    }

    public void clearAllFields(){
        nameTxtInput.setText("");
        codeTxtField.setText("");
        isbnTxtInput.setText("");
        bookcaseTxtInput.setText("");
        bookshelfTxtInput.setText("");
    }

    @Override
    public JComponent getMainPanel() {
        return addQuantityBookPanel;
    }


}
