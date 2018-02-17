package com.tkaczu.hibernate.library.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer book_id;

    @Column(name = "title")
    private String bookTitle;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors = new HashSet<>();


    private Set<Edition> editions = new HashSet<>();


    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    @JoinTable(name = "book_kinds",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "kinds_id")})
    private List<Kinds> kinds;


    public Books() {
    }

    public Books(String bookTitle, Set<Author> authors) {
        this.bookTitle = bookTitle;
        this.authors = authors;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Set<Edition> getEditions() {
        return editions;
    }

    public void setEditions(Set<Edition> editions) {
        this.editions = editions;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public List<Kinds> getKinds() {
        return kinds;
    }

    public void setKinds(List<Kinds> kinds) {
        this.kinds = kinds;
    }
}
