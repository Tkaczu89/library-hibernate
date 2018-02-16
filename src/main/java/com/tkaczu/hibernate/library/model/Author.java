package com.tkaczu.hibernate.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "author", uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"})})

public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long authorId;

    @Column(name = "first_name", unique = true)
    private String firstName;

    @Column(name = "last_name", unique = true)
    private String lastName;

    @OneToMany(mappedBy = "author")
    private List<Books> books;

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
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

        Author author = (Author) o;

        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) return false;
        return lastName != null ? lastName.equals(author.lastName) : author.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
