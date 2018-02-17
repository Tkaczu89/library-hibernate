package com.tkaczu.hibernate.library.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        Transaction trans = null;
        try (SessionFactory factory = new Configuration().configure().buildSessionFactory()) {
            try (Session session = factory.openSession()) {
                trans = session.beginTransaction();


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