package com.bo.acredito.domain;

import javax.persistence.*;
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
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
    private String name;
    @Transient
    private String codeName;
    @Basic
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (available != null ? !available.equals(product.available) : product.available != null) return false;
        if (code != null ? !code.equals(product.code) : product.code != null) return false;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (notes != null ? !notes.equals(product.notes) : product.notes != null) return false;
        if (!Arrays.equals(photo, product.photo)) return false;
        if (price != null ? !price.equals(product.price) : product.price != null) return false;
        if (version != null ? !version.equals(product.version) : product.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (available != null ? available.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (photo != null ? Arrays.hashCode(photo) : 0);
        return result;
    }
}
