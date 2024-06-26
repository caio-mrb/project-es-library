classDiagram
direction BT
class AddLoanPanel {
  + AddLoanPanel() 
  + AddLoanPanel(PartnerPanel) 
  - JComboBox~Book~ bookComboBox
  - getAvailableQuantityBook(LinkedList~QuantityBook~) QuantityBook?
  + clearAllFields() void
   JComponent mainPanel
   LinkedList~Book~ bookComboBox
}
class AddQuantityBookPanel {
  + AddQuantityBookPanel(BaseBookPanel) 
  + AddQuantityBookPanel() 
  - Book book
  - setCode() void
  + clearAllFields() void
   Book startup
   Book book
   JComponent mainPanel
}
class BaseBookPanel {
  + BaseBookPanel() 
  + BaseBookPanel(LinkedList~Book~, LinkedList~String~, LinkedList~String~, LinkedList~Supplier~) 
  ~ MainBookPanel mainBookPanel
  ~ AddQuantityBookPanel addQuantityBookPanel
   AddQuantityBookPanel addQuantityBookPanel
   JComponent mainPanel
   MainBookPanel mainBookPanel
}
class BasePanel {
  + BasePanel(String) 
  + goTo(Class~NamedPanel~, int) void
   JComponent mainPanel
   String panelName
}
class Book {
  + Book(int, String, String, int, String, String, Supplier) 
  - LinkedList~QuantityBook~ quantityBooks
  - String subgenre
  - int requestsCounter
  - String author
  - String genre
  - Supplier supplier
  - int edition
  + toString() String
  + addRequestCounter() void
  + addQuantityBook(QuantityBook) void
   LinkedList~QuantityBook~ quantityBooks
   String subgenre
   int requestsCounter
   Boolean active
   String author
   Supplier supplier
   int edition
   String genre
}
class BookState {
<<enumeration>>
  - BookState(String) 
  + toString() String
  + values() BookState[]
  + valueOf(String) BookState
}
class FillablePanel {
<<Interface>>
  + clearAllFields() void
  + resetAllButtons() void
  + update(Object) void
  + add() void
  + display(Object) void
  + enableAllFields() void
  + disableAllFields() void
}
class FinishLoanPanel {
  + FinishLoanPanel() 
}
class HomePanel {
  + HomePanel() 
   JComponent mainPanel
}
class LibraryApp {
  + LibraryApp() 
  + main(String[]) void
}
class Loan {
  + Loan(int, Book, QuantityBook, int, LocalDate) 
  + Loan(int, Book, QuantityBook, int) 
  + Loan(int, Book, QuantityBook, int, LocalDate, LocalDate, int) 
  - BookState state
  - int deadline
  - LocalDate requestDate
  - LocalDate returnDate
  - Book book
  - int code
  - int loanTaxValue
   int deadline
   BookState state
   LocalDate returnDate
   LocalDate requestDate
   int code
   int loanTaxValue
   Book book
}
class LoanTax {
  + LoanTax(Loan, LoanTaxState) 
  - LoanTaxState loanTaxState
  - Loan loan
   Loan loan
   LoanTaxState loanTaxState
}
class LoanTaxState {
<<enumeration>>
  - LoanTaxState(String) 
  + values() LoanTaxState[]
  + toString() String
  + valueOf(String) LoanTaxState
}
class MainBookPanel {
  + MainBookPanel(LinkedList~Book~, LinkedList~String~, LinkedList~String~, LinkedList~Supplier~, BaseBookPanel) 
  + MainBookPanel() 
  + disableAllFields() void
  + updateSupplierComboBox(boolean) void
  + add() void
  + enableAllFields() void
  + clearAllFields() void
  + resetAllButtons() void
  - initializeQuantityTable(LinkedList~QuantityBook~) void
  + update(Object) void
  + display(Object) void
   JComponent mainPanel
}
class MainLoanPanel {
  + MainLoanPanel(PartnerPanel) 
  + MainLoanPanel() 
  - JButton addNewLoanButton
  - JButton finishLoanButton
  + clearLoanTable() void
  + initializeLoanTable(LinkedList~Loan~) void
  + resetAllButtons() void
   JButton addNewLoanButton
   JComponent mainPanel
   JButton finishLoanButton
}
class MostRequestedBookPanel {
  + MostRequestedBookPanel() 
   JComponent mainPanel
}
class NamedPanel {
<<Interface>>
  + getPanelNameFor(Class~NamedPanel~) String
   String panelName
}
class Partner {
  + Partner(int, String, int, LocalDate, int, String, String, String, String, int, int) 
  + Partner(int, String, int, LocalDate, int, String, String, String, String) 
  + Partner(int, String, int, LocalDate, int, String, String, String) 
  - int phone
  - String email
  - int nif
  - LinkedList~Loan~ loans
  - String postalCode
  - LinkedList~LoanTax~ loanTaxes
  - String address1
  - LocalDate birthdayDate
  - String address2
  - int maxAmountLoan
  - int maxDeadlineLoan
  + addLoan(Loan) void
   LocalDate birthdayDate
   int nif
   int maxDeadlineLoan
   int phone
   String email
   int maxAmountLoan
   Boolean active
   String address2
   LinkedList~LoanTax~ loanTaxes
   LinkedList~Loan~ loans
   String postalCode
   String address1
}
class PartnerPanel {
  + PartnerPanel() 
  + PartnerPanel(LinkedList~Partner~, LinkedList~Book~) 
  - MainLoanPanel mainLoanPanel
  - LinkedList~Book~ books
  - Searchable foundPartner
  - AddLoanPanel addLoanPanel
  - JPanel mainPanel
  - initializeLoanTaxList(LinkedList~LoanTax~) void
  + clearAllFields() void
  + resetAllButtons() void
  + update(Object) void
  + display(Object) void
  + add() void
  + enableAllFields() void
  + disableAllFields() void
   LinkedList~Book~ books
   AddLoanPanel addLoanPanel
   MainLoanPanel mainLoanPanel
   Searchable foundPartner
   JComponent mainPanel
}
class QuantityBook {
  + QuantityBook(int, String, String, String) 
  - String isbn
  - String bookshelf
  - String bookcase
  - int code
  - BookState bookState
   String bookcase
   BookState bookState
   String isbn
   int code
   String bookshelf
}
class RandomBirthDate {
  + RandomBirthDate() 
  - getMaxDaysInMonth(int, int) int
  + generate(int, int) LocalDate
}
class Searchable {
  + Searchable(int, String) 
  - String name
  - int code
  + findBy(LinkedList~Searchable~, Object) Searchable?
   String name
   int code
}
class Supplier {
  + Supplier(int, String, int, int, String, String, String, String) 
  + Supplier(int, String, int, int, String, String, String) 
  - String email
  - String postalCode
  - LinkedList~Book~ books
  - String address2
  - String address1
  - int nif
  - int phone
  + addBook(Book) void
  + toString() String
  + removeBook(Book) void
   int nif
   int phone
   String email
   Boolean active
   LinkedList~Book~ books
   String address2
   String postalCode
   String address1
}
class SupplierPanel {
  + SupplierPanel(LinkedList~Book~, LinkedList~Supplier~) 
  + SupplierPanel() 
  - JPanel mainPanel
  + enableAllFields() void
  + update(Object) void
  + add() void
  + clearAllFields() void
  - initializeBooksList(LinkedList~Book~) void
  + resetAllButtons() void
  + display(Object) void
  + disableAllFields() void
   JComponent mainPanel
}

AddLoanPanel  -->  BasePanel 
AddQuantityBookPanel  -->  BasePanel 
BaseBookPanel  -->  BasePanel 
BasePanel  ..>  NamedPanel 
Book  -->  Searchable 
HomePanel  -->  BasePanel 
MainBookPanel  -->  BasePanel 
MainBookPanel  ..>  FillablePanel 
MainLoanPanel  -->  BasePanel 
MostRequestedBookPanel  -->  BasePanel 
Partner  -->  Searchable 
PartnerPanel  -->  BasePanel 
PartnerPanel  ..>  FillablePanel 
Supplier  -->  Searchable 
SupplierPanel  -->  BasePanel 
SupplierPanel  ..>  FillablePanel 
