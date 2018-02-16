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

                List<Kinds> lotrKinds = new LinkedList<>();
                List<Kinds> harryKinds = new LinkedList<>();

                lotrKinds.add(fantasy);
                lotrKinds.add(thriller);
                lotrKinds.add(adventureNovel);

                harryKinds.add(fantasy);
                harryKinds.add(adventureNovel);
                harryKinds.add(comedy);

                Books book1 = new Books("Wladca Pierscieni: Druzyna pierscienia");
                book1.setAuthor(tolkien);
                book1.setKinds(lotrKinds);
                Books book2 = new Books("Wladca Pierscieni: Powrot krola");
                book2.setAuthor(tolkien);
                book2.setKinds(lotrKinds);
                Books book3 = new Books("Harry Potter i kamien filozoficzny");
                book3.setAuthor(rowling);
                book3.setKinds(harryKinds);


                List<Author> authorsList = session.createQuery("FROM Author").getResultList();

                if (!authorsList.contains(tolkien)) {
                    session.save(tolkien);
                }
                if (!authorsList.contains(tolkien)) {
                    session.save(rowling);
                }

                List<Kinds> kindsList = session.createQuery("FROM Kinds").getResultList();
                if (!kindsList.contains(fantasy)) {
                    session.save(fantasy);
                }
                if (!kindsList.contains(drama)) {
                    session.save(drama);
                }

                if (!kindsList.contains(horror)) {
                    session.save(horror);
                }

                if (!kindsList.contains(adventureNovel)) {
                    session.save(adventureNovel);
                }

                if (!kindsList.contains(thriller)) {
                    session.save(thriller);
                }

                if (!kindsList.contains(comedy)) {
                    session.save(comedy);
                }

                session.save(book1);
                session.save(book2);
                session.save(book3);
                trans.commit();
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

    *//*private static void addPublisher(String pubName, String pubLocation) {
        Transaction trans = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                trans = session.beginTransaction();
                Publisher publisher = new Publisher(2,pubName, pubLocation);
                session.save(publisher);
                trans.commit();
            } catch (RuntimeException ex) {
                if (trans != null) {
                    trans.rollback();
                }
                throw ex;
            }
        }
    }*//*

}*/