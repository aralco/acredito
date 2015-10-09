package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class State {
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String id;
    @Basic
    @NotNull
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 100)
    private String name;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "countryId", referencedColumnName = "id", nullable = false)
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
