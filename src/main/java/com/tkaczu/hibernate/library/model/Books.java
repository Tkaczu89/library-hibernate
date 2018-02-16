package com.tkaczu.hibernate.library.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long book_id;

    @Column(name = "title")
    private String bookTitle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "authors_id")
    private Author author;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<Kinds> kinds;


    public Books() {
    }

    public Books(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Kinds> getKinds() {
        return kinds;
    }

    public void setKinds(List<Kinds> kinds) {
        this.kinds = kinds;
    }
}
