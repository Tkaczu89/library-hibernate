package com.tkaczu.hibernate.library.model;


import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "editions")
public class Edition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edition_id")
    private Integer editionId;

    @Column(name = "edition_number",nullable = false)
    private Integer number;

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "book_id")
    private Books books;

    @ManyToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Edition() {
    }

    public Edition(Integer number, Books books, Publisher publisher) {
        this.number = number;
        this.books = books;
        this.publisher = publisher;
    }

    public Integer getEditionId() {
        return editionId;
    }

    public void setEditionId(Integer editionId) {
        this.editionId = editionId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Books getBook() {
        return books;
    }

    public void setBook(Books book) {
        this.books = book;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
