package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Company {
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String id;
    @Basic
    @NotNull
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250)
    private String name;
    @Basic
    @Lob
    @Size(min = 1)
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 65535)
    private String description;

    public Company() {
    }

    public Company(String name, String description) {
        this.name = name;
        this.description = description;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
