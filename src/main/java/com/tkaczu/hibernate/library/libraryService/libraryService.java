package com.tkaczu.hibernate.library.libraryService;

import com.tkaczu.hibernate.library.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class libraryService {

    public void printBooks(SessionFactory factory) {
        System.out.println("List of all books in library:");
        try (Session session = factory.openSession()) {
            Query<Book> q = session.createQuery("FROM Book");
            List<Book> books = q.getResultList();
            for (Book book : books) {
                System.out.print("ID: " + book.getBook_id() + " Title: " + book.getTitle() + " ");
                book.getAuthors().stream().map(a ->
                        "Authors: " + a.getAuthorId()
                                + " " + a.getFirstName()
                                + " " + a.getLastName()
                                + " " + a.getNick())
                        .forEach(System.out::print);
                book.getKinds().stream().map(k -> "Kinds : " + k.getKind() + " ").forEach(System.out::print);
                System.out.println();
            }
        }
    }
    public void findBookByTitle(SessionFactory factory) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Give me a title: ");
        String title = sc.nextLine().toUpperCase();
        try (Session session = factory.openSession()) {
            Query<Book> q = session.createQuery("FROM Book b WHERE b.title = :title");
            q.setParameter("title", title);
            List<Book> books = q.getResultList();
            if (q.getResultList().size() != 0) {
                for (Book book : books) {
                    System.out.print("ID: " + book.getBook_id() + " Title: " + book.getTitle() + " ");
                    book.getAuthors().stream().map(a ->
                            "Authors: " + a.getAuthorId()
                                    + " " + a.getFirstName()
                                    + " " + a.getLastName()
                                    + " " + a.getNick())
                            .forEach(System.out::print);
                }
            } else {
                System.out.println("We don't have book with that title!");
            }
        }
    }
    public boolean isItBooksInLibrary(SessionFactory factory, Book book) {
        String bookTitle = book.getTitle();
        try (Session session = factory.openSession()) {
            Query<Book> q = session.createQuery("From Book book WHERE book.title =:bookTitle");
            q.setParameter("bookTitle", bookTitle);
            return q.getResultList().contains(book);
        }
    }
    public void findBookByAuthor(SessionFactory factory) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Give me authors first name: ");
        String firstName = sc.nextLine().toUpperCase();
        System.out.print("Give me authors last name: ");
        String lastName = sc.nextLine().toUpperCase();
        try (Session session = factory.openSession()) {
            Query<Book> q = session.createQuery("FROM Book b WHERE b.author.firstName = :firstName ");
            q.setParameter("FirstName", firstName);
            List<Book> books = q.getResultList();
            if (q.getResultList().size() != 0) {
                for (Book book : books) {
                    System.out.println("ID: " + book.getBook_id() + " Title: " + book.getTitle() + " ");
                    book.getAuthors().stream().map(a ->
                            "Authors: " + a.getAuthorId()
                                    + " " + a.getFirstName()
                                    + " " + a.getLastName()
                                    + " " + a.getNick())
                            .forEach(System.out::print);
                }
            } else {
                System.out.println("We don't have book with that author!");
            }
        }
    }
    public boolean isItAuthorInLibrary(SessionFactory factory, Author author) {
        try (Session session = factory.openSession()) {
            Query<Author> q = session.createQuery("FROM Author");
            return q.getResultList().contains(author);
        }
    }
    public boolean isItPublisherInLibrary(SessionFactory factory, Publisher publisher) {
        try (Session session = factory.openSession()) {
            Query<Publisher> q = session.createQuery("FROM Publisher");
            return q.getResultList().contains(publisher);
        }
    }
    public List<Book> findBookByKind(SessionFactory factory, String kind) {
        try (Session session = factory.openSession()) {
            Query<Book> q = session.createQuery("SELECT title FROM books WHERE kind = :kind");
            q.setParameter("kind", kind);
            return q.getResultList();
        }
    }
    public boolean isItKindsInLibrary(SessionFactory factory, Kinds kind) {
        try (Session session = factory.openSession()) {
            Query<Kinds> q = session.createQuery("FROM Kinds");
            return q.getResultList().contains(kind);
        }
    }
    public void insertBook(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Transaction trans = null;

            Scanner sc = new Scanner(System.in);
            Book book = new Book();

            System.out.print("Give me book title: ");
            String title = sc.nextLine().toUpperCase();
            book.setTitle(title);

            List<Kinds> kinds = new LinkedList<>();
            int kindsNumber;
            System.out.print("Give me number of kinds: ");
            kindsNumber = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < kindsNumber; i++) {
                System.out.print(i + 1 + " kind: ");
                String kindName = sc.nextLine().toUpperCase();
                kinds.add(new Kinds(kindName));
            }
            for (Kinds kind : kinds) {
                if (isItKindsInLibrary(factory, kind)) {
                    Query<Kinds> q = session.createQuery("FROM Kinds");
                    int index = q.getResultList().indexOf(kind);
                    book.getKinds().add(q.getResultList().get(index));
                } else {
                    book.getKinds().add(kind);
                }
            }

            List<Author> authors = new LinkedList<>();
            int authorsNumber;
            System.out.print("Give me number of authors: ");
            authorsNumber = sc.nextInt();
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

            for (Author author : authors) {
                if (isItAuthorInLibrary(factory, author)) {
                    Query<Author> q = session.createQuery("FROM Author");
                    int index = q.getResultList().indexOf(author);
                    q.getResultList().get(index).getBooks().add(book);
                    session.save(q.getResultList().get(index));
                } else {
                    author.getBooks().add(book);
                    session.save(author);
                }
            }

            System.out.print("Give me a Publisher: ");
            String publisherName = sc.nextLine().toUpperCase();

            System.out.print("Give me a location of publication: ");
            String publisherLocation = sc.nextLine().toUpperCase();
            Publisher publisher = new Publisher(publisherName, publisherLocation);

            System.out.print("Give me edition number: ");
            int editionNumber = sc.nextInt();
            sc.nextLine();

            Edition edition = new Edition(editionNumber, book, publisher);
            try {
                trans = session.beginTransaction();
                session.save(edition);
                trans.commit();
            } catch (RuntimeException e) {
                if (trans != null) {
                    trans.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}


