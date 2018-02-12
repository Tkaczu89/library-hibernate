package com.tkaczu.hibernate.library.model;

import javax.persistence.*;

@Entity
@Table(name = "author")

public class Author {


    @Id
    @GeneratedValue
    @Column(name="author_id",unique = true)
    private Integer autId;

    @Column(name = "first_name")
    private String autName;

    @Column(name = "last_name")
    private String autLastName





}
