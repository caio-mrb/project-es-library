package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainLoanPanel extends BasePanel{
    private JScrollPane loansScrollPanel;
    private JTable loansTable;
    private JPanel buttonsPanel;
    private JButton finishLoanButton;
    private JButton addNewLoanButton;
    private JPanel mainLoanPanel;

    private PartnerPanel partnerPanel;
    private String[] loansColumnNames;
    private String[] loansData;


    public MainLoanPanel() {
        super("MainLoanPanel");

    }

    public MainLoanPanel(PartnerPanel partnerPanel) {
        this();
        this.partnerPanel = partnerPanel;

        loansColumnNames = new String[]{"Código", "Exemplar", "Prazo", "Estado", "Requisição", "Devolução", "Multa"};
        loansScrollPanel.setViewportView(loansTable);

        ListSelectionModel selectionModel = loansTable.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    finishLoanButton.setEnabled(loansTable.getSelectedRow() != -1);
                }
            }
        });

        addNewLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                partnerPanel.getAddLoanPanel().setBookComboBox(partnerPanel.getBooks());
                goTo(AddLoanPanel.class,0);
            }
        });

        finishLoanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int loanCode = (int) loansTable.getValueAt(loansTable.getSelectedRow(), 0);
                Loan loan = partnerPanel.findLoanByCode(loanCode);
                partnerPanel.getFinishLoanPanel().displayLoan(loan);
                goTo(FinishLoanPanel.class,0);
            }
        });

    }

    public void initializeLoanTable(LinkedList<Loan> loans) {
        Object[][] data = new Object[loans.size()][7];
        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);
            data[i][0] = loan.getCode();
            data[i][1] = loan.getBook();
            data[i][2] = loan.getDeadline();
            data[i][3] = loan.getState();
            data[i][4] = loan.getRequestDate();
            data[i][5] = loan.getReturnDate();
            data[i][6] = loan.getLoanTaxValue();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, loansColumnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        loansTable.setModel(tableModel);
    }

    public JTable getLoanTable() {
        return loansTable;
    }

    public void clearLoanTable(){
        DefaultTableModel model = (DefaultTableModel) loansTable.getModel();
        model.setRowCount(0);
    }

    public void resetAllButtons(){
        addNewLoanButton.setEnabled(false);
    }

    public JButton getFinishLoanButton() {
        return finishLoanButton;
    }

    public JButton getAddNewLoanButton() {
        return addNewLoanButton;
    }

    @Override
    public JComponent getMainPanel() {
        return mainLoanPanel;
    }

}
