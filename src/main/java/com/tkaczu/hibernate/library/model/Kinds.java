package com.tkaczu.hibernate.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Kinds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kind_id")
    private Integer kindId;

    @Column(name = "kind", unique = true)
    private String kind;

    @ManyToMany
    @JoinTable(name = "book_kinds",
            joinColumns = {@JoinColumn(name = "kind_id")},
            inverseJoinColumns = {@JoinColumn(name = "books_id")})
    private List<Books> books;


    public Kinds() {
    }

    public Kinds(String kind) {
        this.kind = kind;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kinds kinds = (Kinds) o;

        return kind != null ? kind.equals(kinds.kind) : kinds.kind == null;
    }

    @Override
    public int hashCode() {
        return kind != null ? kind.hashCode() : 0;
    }
}
