package com.bo.acredito.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Arrays;

/**
 * Created by aralco on 2/11/14.
 */
@Entity
public class Product {
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
    @NotEmpty
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
    private String name;
    @Transient
    private String codeName;
    @Basic
    @NotNull
    @Digits(integer=10, fraction=2)
    @Column(name = "price", nullable = false, insertable = true, updatable = true, length = 18, precision = 18, scale = 2)
    private Double price;
    @Basic
    @Column(name = "available", nullable = false, insertable = true, updatable = true, length = 0, precision = 0)
    private Boolean available;
    @Basic
    @Lob
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    private String notes;
    @Basic
    @Lob
    @Column(name = "photo", nullable = true, insertable = true, updatable = true, length = 65535, precision = 0)
    private byte[] photo;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;
    @ManyToOne
    @JoinColumn(name = "supplierId", referencedColumnName = "id", nullable = false)
    private Supplier supplier;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeName() {
        return code+" - "+name;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
