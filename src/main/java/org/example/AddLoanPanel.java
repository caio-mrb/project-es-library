package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Objects;

public class AddLoanPanel extends BasePanel{
    private JPanel addLoanPanel;
    private JPanel line2Panel;
    private JTextField deadlineTxtInput;
    private JLabel bookLbl;
    private JComboBox<Book> bookComboBox;
    private JLabel deadlineLbl;
    private JLabel daysLbl;
    private JPanel line1Panel;
    private JButton returnBtn;
    private JButton saveBtn;
    private JLabel requestDateLbl;
    private JTextField requestDateTxtInput;
    private JPanel line3Panel;

    private PartnerPanel partnerPanel;
    private MainLoanPanel mainLoanPanel;
    private Partner foundPartner;

    public AddLoanPanel() {
        super("AddLoanPanel");


    }

    public AddLoanPanel(PartnerPanel partnerPanel) {
        this();
        this.partnerPanel = partnerPanel;
        this.mainLoanPanel = partnerPanel.getMainLoanPanel();

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllFields();
                goTo(MainLoanPanel.class,0);
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuantityBook quantityBook;
                if ((quantityBook = getAvailableQuantityBook(((Book) bookComboBox.getSelectedItem()).getQuantityBooks())) == null){
                    JOptionPane.showMessageDialog(null, "Sem unidades disponíveis para este exemplar.",
                            "Empréstimo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Loan loan = new Loan(
                        Integer.parseInt((partnerPanel.getFoundPartner()).getCode() + ""
                                + (((Partner)partnerPanel.getFoundPartner()).getLoans().size()+1)) ,(Book)bookComboBox.getSelectedItem(),quantityBook,Integer.parseInt(deadlineTxtInput.getText()),
                        LocalDate.parse(requestDateTxtInput.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

                quantityBook.setBookState(loan.getState());
                loan.getBook().addRequestCounter();
                ((Partner)partnerPanel.getFoundPartner()).addLoan(loan);
                partnerPanel.getMainLoanPanel().initializeLoanTable(((Partner)partnerPanel.getFoundPartner()).getLoans());
                clearAllFields();
                goTo(MainLoanPanel.class,0);
                }
        });
    }

    private QuantityBook getAvailableQuantityBook(LinkedList<QuantityBook> quantityBooks) {
        for (QuantityBook quantityBook : quantityBooks) {
            if(quantityBook.getBookState() == BookState.RETURNED)
                return quantityBook;
        }
        return null;
    }

    public void setBookComboBox(LinkedList<Book> books) {
        bookComboBox.setModel(new DefaultComboBoxModel<>(books.toArray(new Book[0])));
        bookComboBox.setSelectedIndex(-1);
    }

    public void clearAllFields(){
        bookComboBox.setSelectedIndex(-1);
        requestDateTxtInput.setText("");
        deadlineTxtInput.setText("");
    }

    @Override
    public JComponent getMainPanel() {
        return addLoanPanel;
    }
}
