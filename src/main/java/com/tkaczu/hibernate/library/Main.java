package com.tkaczu.hibernate.library;

import com.tkaczu.hibernate.library.libraryService.libraryService;
import com.tkaczu.hibernate.library.model.Book;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            libraryService libraryService = new libraryService();
            Scanner sc = new Scanner(System.in);
            String input;
            do {
                mainMenu();
                input = sc.next().toUpperCase();
                sc.nextLine();
                switch (input) {
                    case "1":
                        libraryService.printBooks(factory);
                        break;
                    case "2":
                        do {
                            searchMenu();
                            input = sc.nextLine().toUpperCase();
                            switch (input) {
                                case "1":
                                    libraryService.findBookByTitle(factory);
                                    break;
                                case "2":
                                    libraryService.findBookByAuthor(factory);
                                    break;
                                case "3":
                                    System.out.print("Give me a kind: ");
                                    String kind = sc.nextLine().toUpperCase();
                                    List<Book> booksByKind = libraryService.findBookByKind(factory, kind);
                                    if (booksByKind.size() != 0) {
                                        booksByKind.stream().forEach((books) -> libraryService.printBooks(factory));
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
                            insertMenu();
                            input = sc.nextLine().toUpperCase();
                            switch (input) {
                                case "1":
                                    libraryService.insertBook(factory);

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
    private static void searchMenu() {
        System.out.println("Search options:");
        System.out.println("1 - Search books by title.");
        System.out.println("2 - Search book by author.");
        System.out.println("3 - Search books by kind.");
        System.out.println("B - Back.");
        System.out.print("Your choice: ");
    }
    private static void mainMenu() {
        System.out.println("Welcome in our Library");
        System.out.println("What do you want to do? :");
        System.out.println("1 - Show me all books.");
        System.out.println("2 - Show me options with search.");
        System.out.println("3 - Show me options with insert");
        System.out.println("Q - Quit the program");
        System.out.print("Your choice: ");
    }
    private static void insertMenu() {
        System.out.println("Insert options:");
        System.out.println("1 - Insert new book.");
        System.out.println("B - Back");
    }
}
