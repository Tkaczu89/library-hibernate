package com.tkaczu.hibernate.library.model;


import javax.persistence.*;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue
    @Column(name = "book_id",unique = true)
    public Integer bookId;

    @Column(name = "book_title")
    public String bookTitle;


}
