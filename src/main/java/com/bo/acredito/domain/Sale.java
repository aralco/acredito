package com.bo.acredito.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by aralco on 2/5/14.
 */
@Entity
public class Sale {
    private Long id;
    private Long version;
    private Long code;
    private Timestamp date;
    private BigDecimal productPrice;
    private BigDecimal discountedAmount;
    private BigDecimal total;
    private String saleType;
    private BigDecimal initialPayment;
    private BigDecimal residualPayment;
    private Integer paymentQuotes;
    private String notes;
    private Employee employeeByEmployeeId;
    private Customer customerByCustomerId;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "version", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Basic
    @Column(name = "date", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "productPrice", nullable = false, insertable = true, updatable = true, length = 5, precision = 2)
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "discountedAmount", nullable = false, insertable = true, updatable = true, length = 5, precision = 2)
    public BigDecimal getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(BigDecimal discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true, length = 5, precision = 2)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @Column(name = "saleType", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    @Basic
    @Column(name = "initialPayment", nullable = false, insertable = true, updatable = true, length = 5, precision = 2)
    public BigDecimal getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(BigDecimal initialPayment) {
        this.initialPayment = initialPayment;
    }

    @Basic
    @Column(name = "residualPayment", nullable = false, insertable = true, updatable = true, length = 5, precision = 2)
    public BigDecimal getResidualPayment() {
        return residualPayment;
    }

    public void setResidualPayment(BigDecimal residualPayment) {
        this.residualPayment = residualPayment;
    }

    @Basic
    @Column(name = "paymentQuotes", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    public Integer getPaymentQuotes() {
        return paymentQuotes;
    }

    public void setPaymentQuotes(Integer paymentQuotes) {
        this.paymentQuotes = paymentQuotes;
    }

    @Basic
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
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

        Sale sale = (Sale) o;

        if (code != null ? !code.equals(sale.code) : sale.code != null) return false;
        if (date != null ? !date.equals(sale.date) : sale.date != null) return false;
        if (discountedAmount != null ? !discountedAmount.equals(sale.discountedAmount) : sale.discountedAmount != null)
            return false;
        if (id != null ? !id.equals(sale.id) : sale.id != null) return false;
        if (initialPayment != null ? !initialPayment.equals(sale.initialPayment) : sale.initialPayment != null)
            return false;
        if (notes != null ? !notes.equals(sale.notes) : sale.notes != null) return false;
        if (paymentQuotes != null ? !paymentQuotes.equals(sale.paymentQuotes) : sale.paymentQuotes != null)
            return false;
        if (productPrice != null ? !productPrice.equals(sale.productPrice) : sale.productPrice != null) return false;
        if (residualPayment != null ? !residualPayment.equals(sale.residualPayment) : sale.residualPayment != null)
            return false;
        if (saleType != null ? !saleType.equals(sale.saleType) : sale.saleType != null) return false;
        if (total != null ? !total.equals(sale.total) : sale.total != null) return false;
        if (version != null ? !version.equals(sale.version) : sale.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (productPrice != null ? productPrice.hashCode() : 0);
        result = 31 * result + (discountedAmount != null ? discountedAmount.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (saleType != null ? saleType.hashCode() : 0);
        result = 31 * result + (initialPayment != null ? initialPayment.hashCode() : 0);
        result = 31 * result + (residualPayment != null ? residualPayment.hashCode() : 0);
        result = 31 * result + (paymentQuotes != null ? paymentQuotes.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    public Employee getEmployeeByEmployeeId() {
        return employeeByEmployeeId;
    }

    public void setEmployeeByEmployeeId(Employee employeeByEmployeeId) {
        this.employeeByEmployeeId = employeeByEmployeeId;
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }
}
