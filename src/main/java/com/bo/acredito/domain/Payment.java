package com.bo.acredito.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by aralco on 2/1/14.
 */
@Entity
public class Payment {
    private long id;
    private int paymentNumber;
    private Timestamp dueDate;
    private BigDecimal amountDue;
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
    @Column(name = "paymentNumber")
    public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    @Basic
    @Column(name = "dueDate")
    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    @Basic
    @Column(name = "amountDue")
    public BigDecimal getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(BigDecimal amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (paymentNumber != payment.paymentNumber) return false;
        if (amountDue != null ? !amountDue.equals(payment.amountDue) : payment.amountDue != null) return false;
        if (dueDate != null ? !dueDate.equals(payment.dueDate) : payment.dueDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + paymentNumber;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (amountDue != null ? amountDue.hashCode() : 0);
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
