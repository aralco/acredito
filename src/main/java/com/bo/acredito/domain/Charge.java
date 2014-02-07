package com.bo.acredito.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by aralco on 2/5/14.
 */
@Entity
public class Charge {
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
    @Column(name = "date", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Timestamp date;
    @Basic
    @Column(name = "chargeAmount", nullable = false, insertable = true, updatable = true, length = 5, precision = 2)
    private BigDecimal chargeAmount;
    @Basic
    @Column(name = "defaultingAmount", nullable = false, insertable = true, updatable = true, length = 5, precision = 2)
    private BigDecimal defaultingAmount;
    @Basic
    @Column(name = "totalAmount", nullable = false, insertable = true, updatable = true, length = 5, precision = 2)
    private BigDecimal totalAmount;
    @Basic
    @Lob
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    private String notes;

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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public BigDecimal getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(BigDecimal chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public BigDecimal getDefaultingAmount() {
        return defaultingAmount;
    }

    public void setDefaultingAmount(BigDecimal defaultingAmount) {
        this.defaultingAmount = defaultingAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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

        Charge charge = (Charge) o;

        if (chargeAmount != null ? !chargeAmount.equals(charge.chargeAmount) : charge.chargeAmount != null)
            return false;
        if (code != null ? !code.equals(charge.code) : charge.code != null) return false;
        if (date != null ? !date.equals(charge.date) : charge.date != null) return false;
        if (defaultingAmount != null ? !defaultingAmount.equals(charge.defaultingAmount) : charge.defaultingAmount != null)
            return false;
        if (id != null ? !id.equals(charge.id) : charge.id != null) return false;
        if (notes != null ? !notes.equals(charge.notes) : charge.notes != null) return false;
        if (totalAmount != null ? !totalAmount.equals(charge.totalAmount) : charge.totalAmount != null) return false;
        if (version != null ? !version.equals(charge.version) : charge.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (chargeAmount != null ? chargeAmount.hashCode() : 0);
        result = 31 * result + (defaultingAmount != null ? defaultingAmount.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }
}
