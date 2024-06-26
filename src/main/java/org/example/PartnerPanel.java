package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class PartnerPanel extends BasePanel implements FillablePanel {

    private JPanel partnerPanel;
    private JPanel mainPanel;
    private JPanel fieldsPanel;
    private JPanel searchPanel;
    private JPanel buttonsPanel;
    private JTextField nameSearchTxtInput;
    private JButton searchBtn;
    private JButton addNewPartnerBtn;
    private JButton payLoanTaxButton;
    private JButton returnBtn;
    private JButton editBtn;
    private JButton saveBtn;
    private JPanel loansPanel;
    private JPanel loanTaxPanel;
    private JPanel partnerInfoPanel;
    private JTextField maxBooksTxtInput;
    private JTextField deadlineTxtInput;
    private JList<LoanTax> loanTaxList;
    private JPanel line1Panel;
    private JPanel line3Panel;
    private JPanel line4Panel;
    private JPanel line5Panel;
    private JTextField nameTxtInput;
    private JTextField nifTxtInput;
    private JTextField birthdayTxtInput;
    private JTextField phoneTxtInput;
    private JTextField emailTxtInput;
    private JTextField address1TxtInput;
    private JTextField postalCodeTxtInput;
    private JTextField address2TxtInput;
    private JCheckBox activeCheckBox;
    private JLabel nameLbl;
    private JLabel nameSearchLbl;
    private JLabel nifLbl;
    private JLabel birthdayLbl;
    private JLabel phoneLbl;
    private JLabel emailLbl;
    private JLabel address1Lbl;
    private JLabel postalCodeLbl;
    private JLabel address2Lbl;
    private JLabel activeLbl;
    private JLabel maxBooksLbl;
    private JLabel deadlineLbl;
    private JLabel daysLbl;
    private JPanel line2Panel;
    private JLabel codeLbl;
    private JTextField codeTxtInput;
    private JLabel codeSearchLbl;
    private JTextField codeSearchTxtInput;
    private JPanel loansInfoPanel;

    private MainLoanPanel mainLoanPanel;
    private AddLoanPanel addLoanPanel;
    private FinishLoanPanel finishLoanPanel;
    private DefaultListModel<LoanTax> listModel;
    private LinkedList<Partner> partners;
    private LinkedList<Book> books;
    private Searchable foundPartner;



    public PartnerPanel() {
        super("PartnerPanel");

    }

    public PartnerPanel(LinkedList<Partner> partners, LinkedList<Book> books) {
        this();

        this.partners = partners;
        this.listModel = new DefaultListModel<>();
        this.books = books;

        finishLoanPanel = new FinishLoanPanel(this);
        mainLoanPanel = new MainLoanPanel(this);
        addLoanPanel = new AddLoanPanel(this);

        loansInfoPanel.add(mainLoanPanel.getMainPanel(), NamedPanel.getPanelNameFor(MainLoanPanel.class));
        loansInfoPanel.add(addLoanPanel.getMainPanel(), NamedPanel.getPanelNameFor(AddLoanPanel.class));
        loansInfoPanel.add(finishLoanPanel.getMainPanel(), NamedPanel.getPanelNameFor(FinishLoanPanel.class));

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (codeSearchTxtInput.getText().isEmpty() && nameSearchTxtInput.getText().isEmpty()){
                    clearAllFields();
                    resetAllButtons();
                    return;
                }

                if (codeSearchTxtInput.getText().isEmpty() || !codeSearchTxtInput.getText().matches("\\d+")){
                    foundPartner = Searchable.findBy(partners, nameSearchTxtInput.getText());
                }

                if (!codeSearchTxtInput.getText().isEmpty() && codeSearchTxtInput.getText().matches("\\d+"))
                {
                    foundPartner = Searchable.findBy(partners, Integer.parseInt(codeSearchTxtInput.getText()));
                }

                if (foundPartner == null){
                    JOptionPane.showMessageDialog(null, "O livro não foi encontrado.",
                            "Exemplar", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                codeSearchTxtInput.setText(String.valueOf(foundPartner.getCode()));
                nameSearchTxtInput.setText(foundPartner.getName());

                display(foundPartner);
                mainLoanPanel.getAddNewLoanButton().setEnabled(true);
                editBtn.setEnabled(true);
            }
        });

        returnBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(saveBtn.isEnabled() && foundPartner != null){
                    resetAllButtons();
                    disableAllFields();
                    clearAllFields();
                    display(foundPartner);
                    editBtn.setEnabled(true);
                    addNewPartnerBtn.setEnabled(true);
                    return;
                }
                if (nameTxtInput.getText().isEmpty()){
                    goTo(HomePanel.class,0);
                    foundPartner = null;
                    clearAllFields();
                    resetAllButtons();
                    return;
                }

                resetAllButtons();
                disableAllFields();
                clearAllFields();

            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetAllButtons();

                saveBtn.setEnabled(true);
                addNewPartnerBtn.setEnabled(false);
                searchBtn.setEnabled(false);
                mainLoanPanel.getFinishLoanButton().setEnabled(false);
                mainLoanPanel.getAddNewLoanButton().setEnabled(false);

                enableAllFields();
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Integer.parseInt(codeTxtInput.getText()) > partners.size()){
                    add();
                }else{
                    update(foundPartner);
                }

                disableAllFields();
                display(foundPartner);
                resetAllButtons();
                editBtn.setEnabled(true);
            }
        });
    }


    public Loan findLoanByCode(int code){
        LinkedList<Loan> loans = ((Partner)foundPartner).getLoans();
        for (Loan loan : loans){
            if (loan.getCode() == code)
                return loan;
        }
        return null;
    }

    public Searchable getFoundPartner() {
        return foundPartner;
    }

    public LinkedList<Book> getBooks() {
        return books;
    }

    public AddLoanPanel getAddLoanPanel() {
        return addLoanPanel;
    }

    public MainLoanPanel getMainLoanPanel() {
        return mainLoanPanel;
    }

    public FinishLoanPanel getFinishLoanPanel() {
        return finishLoanPanel;
    }



    @Override
    public JComponent getMainPanel() {
        return partnerPanel;
    }

    @Override
    public void display(Object obj) {
        Partner partner = (Partner) obj;
        nameSearchTxtInput.setText(partner.getName());
        codeSearchTxtInput.setText(String.valueOf(partner.getCode()));
        nameTxtInput.setText(partner.getName());
        codeTxtInput.setText(String.valueOf(partner.getCode()));
        nifTxtInput.setText(String.valueOf(partner.getNif()));
        activeCheckBox.setSelected(partner.getActive());
        birthdayTxtInput.setText(partner.getBirthdayDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        phoneTxtInput.setText(String.valueOf(partner.getPhone()));
        emailTxtInput.setText(partner.getEmail());
        address1TxtInput.setText(partner.getAddress1());
        postalCodeTxtInput.setText(partner.getPostalCode());
        address2TxtInput.setText(partner.getAddress2());
        maxBooksTxtInput.setText(String.valueOf(partner.getMaxAmountLoan()));
        deadlineTxtInput.setText(String.valueOf(partner.getMaxDeadlineLoan()));
        mainLoanPanel.initializeLoanTable(partner.getLoans());
        initializeLoanTaxList(partner.getLoanTaxes());
    }

    private void initializeLoanTaxList(LinkedList<LoanTax> loanTaxes) {
        for (LoanTax loanTax : loanTaxes) {
            listModel.addElement(loanTax);
        }
        loanTaxList.setModel(listModel);
    }

    @Override
    public void clearAllFields() {
        nameSearchTxtInput.setText("");
        codeSearchTxtInput.setText("");
        nameTxtInput.setText("");
        codeTxtInput.setText("");
        nifTxtInput.setText("");
        activeCheckBox.setSelected(false);
        birthdayTxtInput.setText("");
        phoneTxtInput.setText("");
        emailTxtInput.setText("");
        address1TxtInput.setText("");
        postalCodeTxtInput.setText("");
        address2TxtInput.setText("");
        maxBooksTxtInput.setText("");
        deadlineTxtInput.setText("");
        mainLoanPanel.clearLoanTable();
        listModel.clear();
    }

    @Override
    public void enableAllFields() {
        nameTxtInput.setEditable(true);
        nifTxtInput.setEditable(true);
        activeCheckBox.setEnabled(true);
        birthdayTxtInput.setEditable(true);
        phoneTxtInput.setEditable(true);
        emailTxtInput.setEditable(true);
        address1TxtInput.setEditable(true);
        postalCodeTxtInput.setEditable(true);
        address2TxtInput.setEditable(true);
        maxBooksTxtInput.setEditable(true);
        deadlineTxtInput.setEditable(true);
    }

    @Override
    public void disableAllFields() {
        nameTxtInput.setEditable(false);
        nifTxtInput.setEditable(false);
        activeCheckBox.setEnabled(false);
        birthdayTxtInput.setEditable(false);
        phoneTxtInput.setEditable(false);
        emailTxtInput.setEditable(false);
        address1TxtInput.setEditable(false);
        postalCodeTxtInput.setEditable(false);
        address2TxtInput.setEditable(false);
        maxBooksTxtInput.setEditable(false);
        deadlineTxtInput.setEditable(false);
    }

    @Override
    public void resetAllButtons() {
        searchBtn.setEnabled(true);
        payLoanTaxButton.setEnabled(false);
        addNewPartnerBtn.setEnabled(true);
        returnBtn.setEnabled(true);
        editBtn.setEnabled(false);
        saveBtn.setEnabled(false);
        mainLoanPanel.resetAllButtons();
    }

    @Override
    public void update(Object obj) {
        Partner partner = (Partner) obj;

        this.partners.remove(partner);

        partner.setName(nameTxtInput.getText());
        partner.setNif(Integer.parseInt(nifTxtInput.getText()));
        partner.setActive(activeCheckBox.isSelected());
        partner.setBirthdayDate(LocalDate.parse(birthdayTxtInput.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        partner.setPhone(Integer.parseInt(phoneTxtInput.getText()));
        partner.setEmail(emailTxtInput.getText());
        partner.setAddress1(address1TxtInput.getText());
        partner.setPostalCode(postalCodeTxtInput.getText());
        partner.setAddress2(address2TxtInput.getText());
        partner.setMaxAmountLoan(Integer.parseInt(maxBooksTxtInput.getText()));
        partner.setMaxDeadlineLoan(Integer.parseInt(deadlineTxtInput.getText()));

        this.partners.add(partner);

    }

    @Override
    public void add() {
        foundPartner = new Partner(
                Integer.parseInt(codeTxtInput.getText()),
                nameTxtInput.getText(),
                Integer.parseInt(nifTxtInput.getText()),
                LocalDate.parse(birthdayTxtInput.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                Integer.parseInt(phoneTxtInput.getText()),
                emailTxtInput.getText(),
                address1TxtInput.getText(),
                postalCodeTxtInput.getText(),
                address2TxtInput.getText(),
                Integer.parseInt(maxBooksTxtInput.getText()),
                Integer.parseInt(deadlineTxtInput.getText())
        );

        this.partners.add((Partner) foundPartner);
    }
}
