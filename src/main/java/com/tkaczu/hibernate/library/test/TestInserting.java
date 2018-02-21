package com.tkaczu.hibernate.library.test;

import com.tkaczu.hibernate.library.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TestInserting {

    public static void main(String[] args) {

        Publisher publisher = new Publisher("Oxford University Press", "Oxford");

        Author nielsen = new Author("Michael", "Nielsen", null);
        Author chuang = new Author("Isaac", "Chuang", null);

        Kinds drama = new Kinds("Drama");
        Kinds historical = new Kinds("Historical");

        Set<Author> authors = new HashSet<Author>();
        authors.add(nielsen);
        authors.add(chuang);

        Set<Kinds> kinds = new HashSet<Kinds>();
        kinds.add(drama);
        kinds.add(historical);

        Book book = new Book("Quantum information and quantum computation", authors, kinds);

        Edition edition = new Edition(1, book, publisher);


        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                Transaction trans = null;
                try {
                    System.out.println("======= Insert START ======");
                    trans = session.beginTransaction();
                    session.save(edition);
                    trans.commit();
                } catch (RuntimeException e) {
                    if (trans != null) {
                        trans.rollback();
                    }
                    e.printStackTrace();
                }

                List<Author> isaac = findByFirstName(factory,"Isaac");
                isaac.forEach(author -> System.out.println(author.getFirstName() + " " + author.getLastName()));

            }
        }
    }
    private static List<Author> findByFirstName(SessionFactory factory,String firstName){
        try(Session session = factory.openSession()){
            Query<Author> q = session.createQuery("FROM Author author WHERE author.firstName = :firstName");
            q.setParameter("firstName",firstName);
            return q.getResultList();
        }
    }
}
