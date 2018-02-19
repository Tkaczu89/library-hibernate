package com.tkaczu.hibernate.library.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer book_id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToMany(mappedBy = "books")
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    private Set<Author> authors = new HashSet<>();

    @OneToMany(mappedBy = "books")
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<Edition> editions = new HashSet<>();


    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinTable(name = "books_kinds",
            joinColumns = {@JoinColumn(name = "book_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "kind_id", nullable = false)})
    private Set<Kinds> kinds = new HashSet<>();


    public Books() {
    }

    public Books(String title, Set<Author> authors, Set<Kinds> kinds) {
        this.title = title;
        authors.forEach(this::addAuthor);
        kinds.forEach(this::addKind);
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Set<Kinds> getKinds() {
        return kinds;
    }

    public void setKinds(Set<Kinds> kinds) {
        this.kinds = kinds;
    }

    public void addAuthor(Author author) {
        authors.add(author);
        author.getBooks().add(this);
    }

    public void addKind(Kinds kind) {
        kinds.add(kind);
        kind.getBooks().add(this);
    }

    public static Books newEdition(String title, Set<Author> authors,Set<Kinds> kinds, Publisher publisher,Integer editionNumber) {
        Books book = new Books (title,authors,kinds);
        book.getEditions().add(new Edition(editionNumber,book,publisher));
        return book;
    }
}
