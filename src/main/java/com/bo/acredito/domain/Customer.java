package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "occupationId", referencedColumnName = "id", nullable = false)
    private Occupation occupation;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "addressId", referencedColumnName = "id", nullable = false)
    private Address address=new Address();

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
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCodeName() {
        return codeName;
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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
