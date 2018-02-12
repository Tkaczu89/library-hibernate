package com.tkaczu.hibernate.library.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue
    @Column(name = "book_id", unique = true)
    public long bookId;

    /*@ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="books_author",
            joinColumns = {@JoinColumn(name="author_id")},
            inverseJoinColumns = {@JoinColumn(name="book_id")})
    private Set<Books> books = new HashSet<>();*/

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "author_id")
    public Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "kind_id")
    public Kinds kinds;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "issue_id")
    public Issue issue;

    @Column(name = "book_title")
    public String bookTitle;

    public Books(Author author, Kinds kinds, Issue issue, String bookTitle) {
        this.author = author;
        this.kinds = kinds;
        this.issue = issue;
        this.bookTitle = bookTitle;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
