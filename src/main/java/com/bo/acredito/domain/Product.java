package com.bo.acredito.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by aralco on 2/1/14.
 */
@Entity
public class Product {
    private long id;
    private long code;
    private String name;
    private boolean available;
    private String notes;
    private byte[] photo;
    private Collection<Price> pricesById;
    private Collection<Sale> salesById;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "available")
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Basic
    @Column(name = "notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Basic
    @Column(name = "photo")
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

        Product product = (Product) o;

        if (available != product.available) return false;
        if (code != product.code) return false;
        if (id != product.id) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (notes != null ? !notes.equals(product.notes) : product.notes != null) return false;
        if (!Arrays.equals(photo, product.photo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (code ^ (code >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        result = 31 * result + (photo != null ? Arrays.hashCode(photo) : 0);
        return result;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<Price> getPricesById() {
        return pricesById;
    }

    public void setPricesById(Collection<Price> pricesById) {
        this.pricesById = pricesById;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<Sale> getSalesById() {
        return salesById;
    }

    public void setSalesById(Collection<Sale> salesById) {
        this.salesById = salesById;
    }
}
