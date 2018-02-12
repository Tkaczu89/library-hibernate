package com.tkaczu.hibernate.library.model;

import javax.persistence.*;

@Entity
@Table(name = "author")

public class Author {

    @Id
    @GeneratedValue
    @Column(name = "author_id", unique = true)
    private long autId;

    @Column(name = "first_name")
    private String autName;

    @Column(name = "last_name")
    private String autLastName;

    public Author(String autName, String autLastName) {
        this.autName = autName;
        this.autLastName = autLastName;
    }

    public long getAutId() {
        return autId;
    }

    public void setAutId(long autId) {
        this.autId = autId;
    }

    public String getAutName() {
        return autName;
    }

    public void setAutName(String autName) {
        this.autName = autName;
    }

    public String getAutLastName() {
        return autLastName;
    }

    public void setAutLastName(String autLastName) {
        this.autLastName = autLastName;
    }
}
