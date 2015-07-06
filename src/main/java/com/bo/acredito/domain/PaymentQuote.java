package com.bo.acredito.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
@Table(name = "PAYMENT_QUOTE", schema = "", catalog = "acredito")
public class PaymentQuote {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;
    @Basic
    @Column(name = "amountDue", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double amountDue;
    @Basic
    @Column(name = "dueDate", nullable = false, insertable = true, updatable = true)
    private Date dueDate;
    @Basic
    @Column(name = "paymentNumber", nullable = false, insertable = true, updatable = true)
    private Integer paymentNumber;
    @Basic
    @Column(name = "mora", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double mora;
    @Basic
    @Column(name = "paymentDate", nullable = true, insertable = true, updatable = true)
    private Date paymentDate;
    @Basic
    @Column(name = "paymentCode", nullable = true, insertable = true, updatable = true)
    private Long paymentCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(Integer paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public Double getMora() {
        return mora;
    }

    public void setMora(Double mora) {
        this.mora = mora;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(Long paymentCode) {
        this.paymentCode = paymentCode;
    }
}
