package com.tkaczu.hibernate.library.model;

import javax.persistence.*;

@Entity
@Table(name = "publisher")


public class Publisher {

    @Id
    @GeneratedValue
    @Column(name = "publisher_id")
    public long pubId;

    @Column(name = "name")
    public String pubName;

    @Column(name = "location")
    public String pubLocation;

    public Publisher(String pubName, String pubLocation) {
        this.pubName = pubName;
        this.pubLocation = pubLocation;
    }

    public long getPubId() {
        return pubId;
    }

    public void setPubId(long pubId) {
        this.pubId = pubId;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public String getPubLocation() {
        return pubLocation;
    }

    public void setPubLocation(String pubLocation) {
        this.pubLocation = pubLocation;
    }
}
