package com.tkaczu.hibernate.library.model;

import javax.persistence.*;

@Entity
@Table(name = "kinds")


public class Kinds {

    @Id
    @GeneratedValue
    @Column(name = "kind_id", unique = true)
    public long kindId;


    @Column(name = "kind_name")
    public String kindName;

    public Kinds(String kindName) {
        this.kindName = kindName;
    }

    public long getKindId() {
        return kindId;
    }

    public void setKindId(long kindId) {
        this.kindId = kindId;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }
}

