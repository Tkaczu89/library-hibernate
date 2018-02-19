package com.tkaczu.hibernate.library.model;

import javax.persistence.*;

@Entity
@Table(name = "publishers")

public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Integer publisherId;

    @Column(name = "publisher_name", unique = true, nullable = false)
    private String name;

    @Column(name = "publication_location")
    private String location;

    public Publisher() {
    }

    public Publisher(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Integer getPublisherId() {

        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
