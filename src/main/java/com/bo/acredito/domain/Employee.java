package com.bo.acredito.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by aralco on 2/5/14.
 */
@Entity
public class Employee {
    private Long id;
    private Long version;
    private Long code;
    private Boolean active;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String idType;
    private String idNumber;
    private Date birthday;
    private byte[] photo;
    private Address addressByAddressId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "version", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Basic
    @Column(name = "active", nullable = false, insertable = true, updatable = true, length = 0, precision = 0)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "username", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "firstName", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "idType", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "idNumber", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Basic
    @Column(name = "birthday", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "photo", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (active != null ? !active.equals(employee.active) : employee.active != null) return false;
        if (birthday != null ? !birthday.equals(employee.birthday) : employee.birthday != null) return false;
        if (code != null ? !code.equals(employee.code) : employee.code != null) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (id != null ? !id.equals(employee.id) : employee.id != null) return false;
        if (idNumber != null ? !idNumber.equals(employee.idNumber) : employee.idNumber != null) return false;
        if (idType != null ? !idType.equals(employee.idType) : employee.idType != null) return false;
        if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
        if (password != null ? !password.equals(employee.password) : employee.password != null) return false;
        if (!Arrays.equals(photo, employee.photo)) return false;
        if (username != null ? !username.equals(employee.username) : employee.username != null) return false;
        if (version != null ? !version.equals(employee.version) : employee.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (idType != null ? idType.hashCode() : 0);
        result = 31 * result + (idNumber != null ? idNumber.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (photo != null ? Arrays.hashCode(photo) : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
