package com.tkaczu.hibernate.library.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kinds", uniqueConstraints = {@UniqueConstraint(columnNames = {"kind"})})
public class Kinds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kind_id")
    private Integer kindId;

    @Column(name = "kind", nullable = false)
    private String kind;

    @ManyToMany(mappedBy = "kinds")
    private Set<Books> books = new HashSet<>();


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

    public Set<Books> getBooks() {
        return books;
    }

    public void setBooks(Set<Books> books) {
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
