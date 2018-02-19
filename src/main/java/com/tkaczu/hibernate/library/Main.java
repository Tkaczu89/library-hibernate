package com.tkaczu.hibernate.library;

import com.tkaczu.hibernate.library.libraryService.BookService;
import com.tkaczu.hibernate.library.model.Author;
import com.tkaczu.hibernate.library.model.Books;
import com.tkaczu.hibernate.library.model.Kinds;
import com.tkaczu.hibernate.library.model.Publisher;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {

            BookService bookService = new BookService();

            Scanner sc = new Scanner(System.in);
            String input;
            do {
                input = getScannerToMainMenu(sc);
                switch (input) {
                    case "1":
                        System.out.println("List of all books in library:");
                        List<Books> allBooks = bookService.getAllBooks(factory);
                        factory.openSession();
                        allBooks.stream().forEach((books) -> bookService.printBooks(factory, books));

                        break;
                    case "2":
                        do {
                            input = getScannerToSearchOptionMenu(sc);
                            switch (input) {
                                case "1":
                                    System.out.print("Give me a title: ");
                                    String title = sc.nextLine().toUpperCase();
                                    List<Books> booksByTitle = bookService.findBookByTitle(factory, title);
                                    if (booksByTitle.size() != 0) {
                                        booksByTitle.stream().forEach((books) -> bookService.printBooks(factory, books));

                                    } else {
                                        System.out.println("We don't have book with that title!");
                                        break;
                                    }
                                    break;
                                case "2":
                                    System.out.print("Give me authors first name: ");
                                    String firstName = sc.nextLine().toUpperCase();
                                    System.out.print("Give me authors last name: ");
                                    String lastName = sc.nextLine().toUpperCase();
                                    Set<Books> booksByAuthor = bookService.findBookByAuthor(factory, firstName, lastName);
                                    if (booksByAuthor.size() != 0) {
                                        System.out.println(booksByAuthor);
                                    } else {
                                        System.out.println("We don't have any book of this author!");
                                        break;
                                    }
                                    break;
                                case "3":
                                    System.out.print("Give me a kind: ");
                                    String kind = sc.nextLine().toUpperCase();

                                    List<Books> booksByKind = bookService.findBookByKind(factory, kind);

                                    if (booksByKind.size() != 0) {
                                        booksByKind.stream().forEach((books) -> bookService.printBooks(factory, books));
                                    } else {
                                        System.out.println("We don't have any book that kind");
                                        break;
                                    }
                                    break;
                                case "B":
                                    System.out.println("Going back!");
                                    break;
                                default:
                                    System.out.println("Bad choice try again!");
                                    break;

                            }
                        } while (!input.equalsIgnoreCase("b"));
                        break;

                    case "3":
                        do {
                            input = getScannerToInsertOptionsMenu(sc);
                            switch (input) {
                                case "1":
                                    insertNewBookScanner(factory, bookService, sc);
                            }
                        } while (!input.equalsIgnoreCase("b"));
                        break;

                    case "Q":
                        System.out.println("See you later!");
                        break;
                    default:
                        System.out.println("Bad choice try again!");
                        break;
                }

            } while (!input.equalsIgnoreCase("q"));
        }
    }

    private static String getScannerToSearchOptionMenu(Scanner sc) {
        String input;
        System.out.println("Search options:");
        System.out.println("1 - Search books by title.");
        System.out.println("2 - Search book by author.");
        System.out.println("3 - Search books by kind.");
        System.out.println("B - Back.");
        input = sc.nextLine().toUpperCase();
        return input;
    }

    private static String getScannerToMainMenu(Scanner sc) {
        String input;
        System.out.println("Welcome in our Library");
        System.out.println("What do you want to do? :");
        System.out.println("1 - Show me all books.");
        System.out.println("2 - Show me options with search.");
        System.out.println("3 - Show me options with insert");
        System.out.println("Q - Quit the program");
        input = sc.nextLine().toUpperCase();
        return input;
    }

    private static String getScannerToInsertOptionsMenu(Scanner sc) {
        String input;
        System.out.println("Insert options:");
        System.out.println("1 - Insert new book.");
        System.out.println("B - Back");
        input = sc.nextLine().toUpperCase();
        return input;
    }

    private static void insertNewBookScanner(SessionFactory factory, BookService bookService, Scanner sc) {
        System.out.print("Give me book title: ");
        String title = sc.nextLine().toUpperCase();

        System.out.print("Give me number of authors: ");
        Set<Author> authors = new HashSet<>();
        int authorsNumber = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < authorsNumber; i++) {
            System.out.print(i + 1 + " authors first name: ");
            String firstName = sc.nextLine().toUpperCase();

            System.out.print(i + 1 + " authors last name: ");
            String lastName = sc.nextLine().toUpperCase();

            System.out.print(i + 1 + " authors nick: ");
            String nick = sc.nextLine().toUpperCase();

            authors.add(new Author(firstName, lastName, nick));
        }


        System.out.print("Give me a Publisher: ");
        String publisherName = sc.nextLine().toUpperCase();

        System.out.print("Give me a location of publication: ");
        String publisherLocation = sc.nextLine().toUpperCase();

        System.out.print("Give me edition number: ");
        int editionNumber = sc.nextInt();
        sc.nextLine();

        Publisher publisher = new Publisher(publisherName, publisherLocation);
        System.out.print("Give me number of kinds: ");
        Set<Kinds> kinds = new HashSet<>();
        int kindsNumber = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < kindsNumber; i++) {
            System.out.print(i + 1 + " kind: ");
            String kindName = sc.nextLine().toUpperCase();
            kinds.add(new Kinds(kindName));
        }
        Books book = new Books();

        book.setTitle(title);
        book.setKinds(kinds);
        bookService.insertBook(factory, publisher, book, editionNumber);
    }
}
