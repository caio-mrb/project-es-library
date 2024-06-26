package org.example;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class LibraryApp {

    public static void main(String[] args) {
        int index;

        Random rand = new Random();

        LinkedList<String> genres = new LinkedList<>();
        LinkedList<String> subgenres = new LinkedList<>();
        LinkedList<Supplier> suppliers = new LinkedList<>();
        LinkedList<Book> books = new LinkedList<>();
        LinkedList<Partner> partners = new LinkedList<>();

        genres.add("Ficção");
        genres.add("Não-ficção");
        genres.add("Fantasia");
        genres.add("Romance");
        genres.add("Mistério");

        subgenres.add("Ficção Científica");
        subgenres.add("Biografia");
        subgenres.add("Fantasia Épica");
        subgenres.add("Romance Histórico");
        subgenres.add("Thriller Psicológico");

        String[] suppliersNames = {"Bertrand Livreiros", "Fnac Portugal", "Wook", "Leya", "Almedina"};

        index = 0;
        for (String supplierName : suppliersNames) {
            int spaceIndex = supplierName.indexOf(' ');
            suppliers.add(new Supplier(index+1,supplierName,rand.nextInt(900000000) + 100000000,
                    rand.nextInt(900000000) + 100000000,
                    "geral@"
                            + ((spaceIndex == -1) ? supplierName.toLowerCase() : supplierName.substring(0, spaceIndex)).toLowerCase()
                            + ".pt",
                    "Rua " + supplierName,
                    rand.nextInt(100) + 2400 + "-" + String.format("%03d",rand.nextInt(999) + 1)));
            index++;
        }

        String[] booksNames = {"1984", "Orgulho e Preconceito", "O Senhor dos Anéis", "O Código Da Vinci", "A História Secreta"};
        String[] authorNames = {"George Orwell", "Jane Austen", "J.R.R. Tolkien", "Dan Brown", "Donna Tartt"};

        index = 0;
        for (String bookName : booksNames) {
            books.add(new Book(index+1,bookName,authorNames[index],1,genres.get(index), subgenres.get(index), suppliers.get(index)));
            suppliers.get(index).addBook(books.getLast());
            index++;
        }

        String[] partnersNames = {"João Silva","Pedro Santos","Miguel Oliveira","Lucas Pereira","Gabriel Rodrigues",
                                  "Maria Martins","Ana Fernandes","Julia Gomes","Sofia Lopes","Isabella Almeida"};

        index = 0;
        for (String partnerName : partnersNames) {
            int spaceIndex = partnerName.indexOf(' ');
            partners.add(new Partner(index+1,partnerName,rand.nextInt(900000000) + 100000000,
                    RandomBirthDate.generate(18,30),rand.nextInt(900000000) + 100000000,
                    partnerName.substring(0,spaceIndex).toLowerCase() + "_"
                            + partnerName.substring(spaceIndex + 1).toLowerCase() + "@gmail.com" ,
                    "Rua "+partnerName,
                    rand.nextInt(100) + 2400 + "-" + String.format("%03d",rand.nextInt(999) + 1)));
            index++;
        }

        JFrame frame = new JFrame("ES Library");

        Container pane = frame.getContentPane();
        pane.setLayout(new CardLayout());
        pane.add(new HomePanel().getMainPanel(), NamedPanel.getPanelNameFor(HomePanel.class));
        pane.add(new PartnerPanel(partners,books).getMainPanel(), NamedPanel.getPanelNameFor(PartnerPanel.class));
        pane.add(new BaseBookPanel(books, genres, subgenres, suppliers).getMainPanel(), NamedPanel.getPanelNameFor(BaseBookPanel.class));
        pane.add(new SupplierPanel(books, suppliers).getMainPanel(), NamedPanel.getPanelNameFor(SupplierPanel.class));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
