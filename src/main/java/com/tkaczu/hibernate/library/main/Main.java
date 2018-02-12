package com.tkaczu.hibernate.library.main;

import com.tkaczu.hibernate.library.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        Author author = new Author("Magdalena", "Nowak");
        Kinds kind = new Kinds("Horror");
        Issue issue = new Issue(2, Date.valueOf("2015-12-02"));
        addBook(author, kind, issue, "Orki z Majorki");

    }

    private static void addAuthor(String autName, String autLastName) {
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

    private static void addPublisher(String pubName, String pubLocation) {
        Transaction trans = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                trans = session.beginTransaction();
                Publisher publisher = new Publisher(pubName, pubLocation);
                session.save(publisher);
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