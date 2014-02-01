package com.bo.acredito.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by aralco on 2/1/14.
 */
@Entity
public class Charge {
    private long id;
    private long code;
    private Timestamp date;
    private BigDecimal chargeAmount;
    private BigDecimal defaultingAmount;
    private BigDecimal totalAmount;
    private String notes;
    private Sale saleBySaleId;

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
    @Column(name = "date")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "chargeAmount")
    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    @Basic
    @Column(name = "defaultingAmount")
    public BigDecimal getDefaultingAmount() {
        return defaultingAmount;
    }

    public void setDefaultingAmount(BigDecimal defaultingAmount) {
        this.defaultingAmount = defaultingAmount;
    }

    @Basic
    @Column(name = "totalAmount")
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "notes")
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

        Charge charge = (Charge) o;

        if (code != charge.code) return false;
        if (id != charge.id) return false;
        if (chargeAmount != null ? !chargeAmount.equals(charge.chargeAmount) : charge.chargeAmount != null)
            return false;
        if (date != null ? !date.equals(charge.date) : charge.date != null) return false;
        if (defaultingAmount != null ? !defaultingAmount.equals(charge.defaultingAmount) : charge.defaultingAmount != null)
            return false;
        if (notes != null ? !notes.equals(charge.notes) : charge.notes != null) return false;
        if (totalAmount != null ? !totalAmount.equals(charge.totalAmount) : charge.totalAmount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (code ^ (code >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (chargeAmount != null ? chargeAmount.hashCode() : 0);
        result = 31 * result + (defaultingAmount != null ? defaultingAmount.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "saleId", referencedColumnName = "id", nullable = false)
    public Sale getSaleBySaleId() {
        return saleBySaleId;
    }

    public void setSaleBySaleId(Sale saleBySaleId) {
        this.saleBySaleId = saleBySaleId;
    }
}
