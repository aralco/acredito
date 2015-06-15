package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
@Table(name = "PAYMENT_PLAN", schema = "", catalog = "acredito")
public class PaymentPlan {
    private Long id;
    private String name;
    private Integer defaultAmount;
    private Integer quotesNumber;
    private Byte active;
    private String description;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "defaultAmount", nullable = false, insertable = true, updatable = true)
    public Integer getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(Integer defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    @Basic
    @Column(name = "quotesNumber", nullable = false, insertable = true, updatable = true)
    public Integer getQuotesNumber() {
        return quotesNumber;
    }

    public void setQuotesNumber(Integer quotesNumber) {
        this.quotesNumber = quotesNumber;
    }

    @Basic
    @Column(name = "active", nullable = false, insertable = true, updatable = true)
    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentPlan that = (PaymentPlan) o;

        if (active != null ? !active.equals(that.active) : that.active != null) return false;
        if (defaultAmount != null ? !defaultAmount.equals(that.defaultAmount) : that.defaultAmount != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (quotesNumber != null ? !quotesNumber.equals(that.quotesNumber) : that.quotesNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (defaultAmount != null ? defaultAmount.hashCode() : 0);
        result = 31 * result + (quotesNumber != null ? quotesNumber.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
