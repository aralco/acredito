package com.bo.acredito.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;

/**
 * Created by aralco on 2/5/14.
 */
@Entity
public class Customer {
    private Long id;
    private String version;
    private Long code;
    private String firstName;
    private String lastName;
    private String salutation;
    private String idType;
    private String idNumber;
    private Date birthday;
    private byte[] photo;
    private String notes;
    private Address addressByAddressId;
    private Contact contactByContactId1;
    private Contact contactByContactId2;
    private Contact contactByContactId3;
    private City cityByCityId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "version", nullable = false, insertable = true, updatable = true, length = 16777215, precision = 0)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
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
    @Column(name = "salutation", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
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
    @Column(name = "photo", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (birthday != null ? !birthday.equals(customer.birthday) : customer.birthday != null) return false;
        if (code != null ? !code.equals(customer.code) : customer.code != null) return false;
        if (firstName != null ? !firstName.equals(customer.firstName) : customer.firstName != null) return false;
        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;
        if (idNumber != null ? !idNumber.equals(customer.idNumber) : customer.idNumber != null) return false;
        if (idType != null ? !idType.equals(customer.idType) : customer.idType != null) return false;
        if (lastName != null ? !lastName.equals(customer.lastName) : customer.lastName != null) return false;
        if (notes != null ? !notes.equals(customer.notes) : customer.notes != null) return false;
        if (!Arrays.equals(photo, customer.photo)) return false;
        if (salutation != null ? !salutation.equals(customer.salutation) : customer.salutation != null) return false;
        if (version != null ? !version.equals(customer.version) : customer.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (salutation != null ? salutation.hashCode() : 0);
        result = 31 * result + (idType != null ? idType.hashCode() : 0);
        result = 31 * result + (idNumber != null ? idNumber.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (photo != null ? Arrays.hashCode(photo) : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
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

    @ManyToOne
    @JoinColumn(name = "contactId1", referencedColumnName = "id", nullable = false)
    public Contact getContactByContactId1() {
        return contactByContactId1;
    }

    public void setContactByContactId1(Contact contactByContactId1) {
        this.contactByContactId1 = contactByContactId1;
    }

    @ManyToOne
    @JoinColumn(name = "contactId2", referencedColumnName = "id", nullable = false)
    public Contact getContactByContactId2() {
        return contactByContactId2;
    }

    public void setContactByContactId2(Contact contactByContactId2) {
        this.contactByContactId2 = contactByContactId2;
    }

    @ManyToOne
    @JoinColumn(name = "contactId3", referencedColumnName = "id", nullable = false)
    public Contact getContactByContactId3() {
        return contactByContactId3;
    }

    public void setContactByContactId3(Contact contactByContactId3) {
        this.contactByContactId3 = contactByContactId3;
    }

    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "id", nullable = false)
    public City getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(City cityByCityId) {
        this.cityByCityId = cityByCityId;
    }
}
