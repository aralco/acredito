package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Occupation {
    @Id
    @GeneratedValue(generator = "Occupation")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String id;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
