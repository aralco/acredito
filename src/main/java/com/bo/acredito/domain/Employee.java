package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by aralco on 2/11/14.
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long id;
    @Version
    @Column(name = "version", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long version;
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long code;
    @Basic
    @Column(name = "active", nullable = false, insertable = true, updatable = true, length = 0, precision = 0)
    private Boolean active;
    @Basic
    @Column(name = "username", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String password;
    @Basic
    @Column(name = "role", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String role;
    @Basic
    @Column(name = "firstName", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    private String firstName;
    @Basic
    @Column(name = "lastName", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    private String lastName;
    @Basic
    @Column(name = "idType", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    private String idType;
    @Basic
    @Column(name = "idNumber", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String idNumber;
    @Basic
    @Column(name = "birthday", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private Date birthday;
    @Basic
    @Lob
    @Column(name = "photo", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    private byte[] photo;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;

    @ManyToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
    private Address address;

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
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
}
