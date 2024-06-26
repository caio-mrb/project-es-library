package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class FinishLoanPanel extends BasePanel {
    private JPanel finishLoanPanel;
    private JPanel line1Panel;
    private JTextField nameTxtInput;
    private JTextField codeTxtInput;
    private JPanel line2Panel;
    private JTextField bookStateTxtInput;
    private JTextField deadlineTxtInput;
    private JPanel line3Panel;
    private JPanel buttonsPanel;
    private JLabel bookStateLbl;
    private JLabel deadlineLbl;
    private JLabel nameLbl;
    private JLabel codeLbl;
    private JTextField requestDateTxtInput;
    private JTextField returnDateTxtInput;
    private JLabel requestDateLbl;
    private JLabel returnDateLbl;
    private JButton saveBtn;
    private JButton returnBtn;

    private PartnerPanel partnerPanel;
    private Loan loan;

    public FinishLoanPanel() {
        super("FinishLoanPanel");

    }

    public FinishLoanPanel(PartnerPanel partnerPanel) {
        this();

        this.partnerPanel = partnerPanel;


        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goTo(MainLoanPanel.class,0);
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public void displayLoan(Loan loan) {
        nameTxtInput.setText(loan.getBook().getName());
        codeTxtInput.setText(String.valueOf(loan.getCode()));
        bookStateTxtInput.setText(loan.getState().toString());
        deadlineTxtInput.setText(String.valueOf(loan.getDeadline()));
        requestDateTxtInput.setText(loan.getRequestDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

    }


    @Override
    public JComponent getMainPanel() {
        return finishLoanPanel;
    }
}
