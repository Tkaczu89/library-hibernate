package com.tkaczu.hibernate.library.libraryService;

import com.tkaczu.hibernate.library.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Set;

public class BookService {


    public void printBooks(SessionFactory factory, Books book) {
        try (Session session = factory.openSession()) {
            System.out.println("ID: " + book.getBook_id() + " Title: " + book.getTitle());
            Set<Author> authors = book.getAuthors();
            for (int i = 0; i < book.getAuthors().size(); i++) {
                System.out.println("Author: " + authors.toArray().toString());
            }

        }
    }


    public List<Books> getAllBooks(SessionFactory factory) {
        try (Session session = factory.openSession()) {
            Query<Books> q = session.createQuery("FROM Books");
            return q.getResultList();
        }
    }

    public List<Books> findBookByTitle(SessionFactory factory, String title) {
        try (Session session = factory.openSession()) {
            Query<Books> q = session.createQuery("FROM Books books WHERE books.title = :title");
            q.setParameter("title", title);
            return q.getResultList();
        }
    }

    public boolean isItBooksInLibrary(SessionFactory factory, Books book) {
        String bookTitle = book.getTitle();
        try (Session session = factory.openSession()) {
            Query<Books> q = session.createQuery("From Books books WHERE books.title =:bookTitle");
            q.setParameter("bookTitle", bookTitle);
            return q.getResultList().size() != 0;
        }
    }

    public Set<Books> findBookByAuthor(SessionFactory factory, String firstName, String lastName) {
        try (Session session = factory.openSession()) {
            Query<Author> q = session.createQuery("FROM Author author WHERE author.firstName = :firstName AND author.lastName=:lastName");


            return q.setParameter("firstName", firstName).setParameter("lastName", lastName).getSingleResult().getBooks();
        }
    }

    public boolean isItAuthorInLibrary(SessionFactory factory, Books book) {
        Set<Author> checkedAuthor = book.getAuthors();
        try (Session session = factory.openSession()) {
            Query<Author> q = session.createQuery("FROM Author");
            return q.getResultList().contains(checkedAuthor.iterator());
        }
    }

    public List<Books> findBookByKind(SessionFactory factory, String kind) {
        try (Session session = factory.openSession()) {
            Query<Books> q = session.createQuery("SELECT title FROM books WHERE kind = :kind");
            q.setParameter("kind", kind);
            return q.getResultList();

        }
    }

    public boolean isItKindsInLibrary(SessionFactory factory, Books book) {
        Set<Kinds> checkedKind = book.getKinds();
        try (Session session = factory.openSession()) {
            Query<Kinds> q = session.createQuery("FROM Kinds");
            return q.getResultList().contains(checkedKind.iterator());
        }
    }


    public void insertBook(SessionFactory factory, Publisher publisher, Books book, Integer editionNumber) {


        try (Session session = factory.openSession()) {
            Transaction trans = null;


            /*Query<Books> q = session.createQuery("FROM Books books WHERE books.title = :book.getTitle()");
            q.setParameter("title", book.getTitle());*/
            if (isItBooksInLibrary(factory, book)) {
                for (Author author : book.getAuthors()) {
                    if (isItAuthorInLibrary(factory, book)) {
                        book.addAuthor(author);
                        book.getAuthors().remove(author);
                    } else {
                        book.setAuthors(book.getAuthors());
                    }
                }
                for (Kinds kind : book.getKinds()) {
                    if (isItKindsInLibrary(factory, book)) {
                        book.addKind(kind);
                        book.getKinds().remove(kind);
                    } else {
                        book.setKinds(book.getKinds());
                    }
                }
            }


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


