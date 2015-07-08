package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;
    @Version
    @Column(name = "version", nullable = false, insertable = true, updatable = true)
    private Long version;
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true)
    private Long code;
    @Basic
    @Column(name = "firstName", nullable = false, insertable = true, updatable = true, length = 100)
    private String firstName;
    @Basic
    @Column(name = "lastName", nullable = false, insertable = true, updatable = true, length = 100)
    private String lastName;
    @Basic
    @Column(name = "companyName", nullable = true, insertable = true, updatable = true, length = 100)
    private String companyName;
    @Basic
    @Column(name = "nit", nullable = true, insertable = true, updatable = true, length = 45)
    private String nit;
    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 65535)
    private String description;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
    private Address address = new Address();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    public String toString() {
        return firstName+" "+lastName;
    }
}
