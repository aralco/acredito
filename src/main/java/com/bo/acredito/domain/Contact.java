package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Contact {
    @Id
    @GeneratedValue(generator = "Contact")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String id;
    @Basic
    @NotNull
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250)
    private String name;
    @Basic
    @NotNull
    @Column(name = "phone", nullable = false, insertable = true, updatable = true, length = 45)
    private String phone;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }
}
