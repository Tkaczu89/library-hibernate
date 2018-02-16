package com.tkaczu.hibernate.library.main;

import com.tkaczu.hibernate.library.model.Author;
import com.tkaczu.hibernate.library.model.Books;
import com.tkaczu.hibernate.library.model.Kinds;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.LinkedList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Transaction trans = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                trans = session.beginTransaction();

                Author tolkien = new Author("John", "Tolkien");
                Author rowling = new Author("Joanne", "Rowling");

                Kinds fantasy = new Kinds("Fantstyka");
                Kinds drama = new Kinds("Dramat");
                Kinds horror = new Kinds("Horror");
                Kinds adventureNovel = new Kinds("Powiesc Przygodowa");
                Kinds thriller = new Kinds("Thriller");
                Kinds comedy = new Kinds("Komedia");

                List<Books> fantasyBooks = new LinkedList<>();
                List<Books> comedyBooks = new LinkedList<>();
                List<Books> thrillerBooks = new LinkedList<>();
                List<Books> adventureNovelBooks = new LinkedList<>();

                Books book1 = new Books("Wladca Pierscieni: Druzyna pierscienia");
                Books book2 = new Books("Wladca Pierscieni: Powrot krola");
                Books book3 = new Books("Harry Potter i kamien filozoficzny");

                fantasyBooks.add(book1);
                fantasyBooks.add(book2);
                fantasyBooks.add(book3);
                comedyBooks.add(book3);
                thrillerBooks.add(book1);
                thrillerBooks.add(book2);
                adventureNovelBooks.add(book1);
                adventureNovelBooks.add(book2);
                adventureNovelBooks.add(book3);


                fantasy.setBooks(fantasyBooks);
                comedy.setBooks(comedyBooks);
                thriller.setBooks(thrillerBooks);
                adventureNovel.setBooks(adventureNovelBooks);

                book1.setAuthor(tolkien);
                book2.setAuthor(tolkien);
                book3.setAuthor(rowling);

                session.save(book1);
                session.save(book2);
                session.save(book3);
                session.save(tolkien);
                session.save(rowling);
                session.save(fantasy);
                session.save(drama);
                session.save(horror);
                session.save(adventureNovel);
                session.save(thriller);
                session.save(comedy);

                trans.commit();
            } catch (RuntimeException ex) {
                if (trans != null) {
                    trans.rollback();
                }
                throw ex;
            }
        }


    }

    private static void addBook(String title, Author author, Kinds kind) {
        Transaction trans = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                trans = session.beginTransaction();
                Books book = new Books(title);


                trans.commit();
            } catch (RuntimeException ex) {
                if (trans != null) {
                    trans.rollback();
                }
                throw ex;
            }
        }

    }
}

   /* private static void addAuthor(String autName, String autLastName) {
        Transaction trans = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                trans = session.beginTransaction();
                Author author = new Author(autName, autLastName);
                session.save(author);
                trans.commit();
            } catch (RuntimeException ex) {
                if (trans != null) {
                    trans.rollback();
                }
                throw ex;
            }
        }
    }

    private static void addBook(Author author, Kinds kind, Issue issue, String title) {
        Transaction trans = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                trans = session.beginTransaction();
                Books book = new Books(author, kind, issue, title);
                session.save(book);
                trans.commit();
            } catch (RuntimeException ex) {
                if (trans != null) {
                    trans.rollback();
                }
                throw ex;
            }
        }
    }

