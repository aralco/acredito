package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
@Table(name = "PAYMENT_PLAN", schema = "", catalog = "acredito")
public class PaymentPlan {
    @Id
    @GeneratedValue(generator = "PaymentPlan")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String id;
    @Basic
    @NotNull
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250)
    private String name;
    @Basic
    @NotNull
    @Digits(integer=10, fraction=0)
    @Column(name = "defaultAmount", nullable = false, insertable = true, updatable = true)
    private Integer defaultAmount;
    @Basic
    @NotNull
    @Digits(integer=10, fraction=0)
    @Column(name = "quotesNumber", nullable = false, insertable = true, updatable = true)
    private Integer quotesNumber;
    @Basic
    @Column(name = "active", nullable = false, insertable = true, updatable = true)
    private Boolean active;
    @Basic
    @Lob
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 65535)
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(Integer defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public Integer getQuotesNumber() {
        return quotesNumber;
    }

    public void setQuotesNumber(Integer quotesNumber) {
        this.quotesNumber = quotesNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
