package com.bo.acredito.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Product {
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
    @NotEmpty
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250)
    private String name;
    @Basic
    @NotNull
    @Digits(integer=10, fraction=2)
    @Column(name = "price", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double price;
    @Basic
    @Column(name = "available", nullable = false, insertable = true, updatable = true)
    private Boolean available;
    @Basic
    @Lob
    @NotNull
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 65535)
    private String notes;
    @Basic
    @Lob
    @Column(name = "photo", nullable = true, insertable = true, updatable = true, length = 65535)
    private byte[] photo;
    @Basic
    @NotNull
    @Column(name = "quantity", nullable = false, insertable = true, updatable = true)
    private Integer quantity;
    @Basic
    @Column(name = "reservedQuantity", nullable = false, insertable = true, updatable = true)
    private Integer reservedQuantity;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "supplierId", referencedColumnName = "id", nullable = false)
    private Supplier supplier;
    @OneToMany
    private Set<SaleProduct> saleProducts;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Integer reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
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

    public Set<SaleProduct> getSaleProducts() {
        return saleProducts;
    }

    public void setSaleProducts(Set<SaleProduct> saleProducts) {
        this.saleProducts = saleProducts;
    }

    public String toString(){return code+" "+name;}
}
