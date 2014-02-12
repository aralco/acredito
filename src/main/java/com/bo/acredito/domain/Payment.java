package com.bo.acredito.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aralco on 2/11/14.
 */
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long id;
    @Basic
    @Column(name = "paymentNumber", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private Integer paymentNumber;
    @Basic
    @Temporal(TemporalType.DATE)
    @Column(name = "dueDate", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private Date dueDate;
    @Basic
    @Column(name = "amountDue", nullable = false, insertable = true, updatable = true, length = 18, precision = 18, scale = 2)
    private Double amountDue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (amountDue != null ? !amountDue.equals(payment.amountDue) : payment.amountDue != null) return false;
        if (dueDate != null ? !dueDate.equals(payment.dueDate) : payment.dueDate != null) return false;
        if (id != null ? !id.equals(payment.id) : payment.id != null) return false;
        if (paymentNumber != null ? !paymentNumber.equals(payment.paymentNumber) : payment.paymentNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (paymentNumber != null ? paymentNumber.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (amountDue != null ? amountDue.hashCode() : 0);
        return result;
    }
}
