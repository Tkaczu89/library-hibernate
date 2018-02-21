package com.tkaczu.hibernate.library.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors", uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name", "nick"})})

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer authorId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "nick")
    private String nick;

    @ManyToMany
    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(name = "authors_books",
            joinColumns = {@JoinColumn(name = "author_id", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "book_id", nullable = false)})
    private Set<Book> books = new HashSet<>();

    public Author() {
    }

    public Author(String firstName, String lastName, String nick) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nick = nick;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) return false;
        if (lastName != null ? !lastName.equals(author.lastName) : author.lastName != null) return false;
        return nick != null ? nick.equals(author.nick) : author.nick == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        return result;
    }
}
