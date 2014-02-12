package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by aralco on 2/11/14.
 */
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long id;
    @Version
    @Column(name = "version", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long version;
    @Basic
    @NotNull
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long code;
    @Basic
    @NotNull
    @Column(name = "firstName", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    private String firstName;
    @Basic
    @NotNull
    @Column(name = "lastName", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    private String lastName;
    @Transient
    private String fullName;
    @Transient
    private String codeName;
    @Basic
    @NotNull
    @Column(name = "salutation", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String salutation;
    @Basic
    @NotNull
    @Column(name = "idType", nullable = false, insertable = true, updatable = true, length = 8, precision = 0)
    @Enumerated(EnumType.STRING)
    private IdTypeEnum idType;
    @Basic
    @NotNull
    @Column(name = "idNumber", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String idNumber;
    @Basic
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private Date birthday;
    @Basic
    @Lob
    @Column(name = "photo", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    private byte[] photo;
    @Basic
    @Lob
    @NotNull
    @Size(min = 1)
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    private String notes;
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
    private Address address=new Address();
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "contactId1", referencedColumnName = "id", nullable = false)
    private Contact contact1;
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "contactId2", referencedColumnName = "id", nullable = false)
    private Contact contact2;
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "contactId3", referencedColumnName = "id", nullable = false)
    private Contact contact3;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "occupationId", referencedColumnName = "id", nullable = false)
    private Occupation occupation;

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

    public String getFullName() {
        return firstName+" "+lastName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCodeName() {
        return code+" - "+firstName+" "+lastName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public IdTypeEnum getIdType() {
        return idType;
    }

    public void setIdType(IdTypeEnum idType) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact1() {
        return contact1;
    }

    public void setContact1(Contact contact1) {
        this.contact1 = contact1;
    }

    public Contact getContact2() {
        return contact2;
    }

    public void setContact2(Contact contact2) {
        this.contact2 = contact2;
    }

    public Contact getContact3() {
        return contact3;
    }

    public void setContact3(Contact contact3) {
        this.contact3 = contact3;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }
}
