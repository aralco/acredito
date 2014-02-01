package com.bo.acredito.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by aralco on 2/1/14.
 */
@Entity
public class Sale {
    private Long id;
    private Long code;
    private Timestamp date;
    private BigDecimal subTotal;
    private BigDecimal discountedAmount;
    private BigDecimal total;
    private String saleType;
    private BigDecimal initialPayment;
    private BigDecimal readidualPayment;
    private String notes;
    private Customer customerByCustomerId;
    private Employee employeeByEmployeeId;
    private Product productByProductId;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
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
    @Column(name = "subTotal")
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    @Basic
    @Column(name = "discountedAmount")
    public BigDecimal getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(BigDecimal discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    @Basic
    @Column(name = "total")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @Column(name = "saleType")
    public String getSaleType() {
        return saleType;
    }

    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }

    @Basic
    @Column(name = "initialPayment")
    public BigDecimal getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(BigDecimal initialPayment) {
        this.initialPayment = initialPayment;
    }

    @Basic
    @Column(name = "readidualPayment")
    public BigDecimal getReadidualPayment() {
        return readidualPayment;
    }

    public void setReadidualPayment(BigDecimal readidualPayment) {
        this.readidualPayment = readidualPayment;
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

        Sale sale = (Sale) o;

        if (code != null ? !code.equals(sale.code) : sale.code != null) return false;
        if (date != null ? !date.equals(sale.date) : sale.date != null) return false;
        if (discountedAmount != null ? !discountedAmount.equals(sale.discountedAmount) : sale.discountedAmount != null)
            return false;
        if (id != null ? !id.equals(sale.id) : sale.id != null) return false;
        if (initialPayment != null ? !initialPayment.equals(sale.initialPayment) : sale.initialPayment != null)
            return false;
        if (notes != null ? !notes.equals(sale.notes) : sale.notes != null) return false;
        if (readidualPayment != null ? !readidualPayment.equals(sale.readidualPayment) : sale.readidualPayment != null)
            return false;
        if (saleType != null ? !saleType.equals(sale.saleType) : sale.saleType != null) return false;
        if (subTotal != null ? !subTotal.equals(sale.subTotal) : sale.subTotal != null) return false;
        if (total != null ? !total.equals(sale.total) : sale.total != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (subTotal != null ? subTotal.hashCode() : 0);
        result = 31 * result + (discountedAmount != null ? discountedAmount.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (saleType != null ? saleType.hashCode() : 0);
        result = 31 * result + (initialPayment != null ? initialPayment.hashCode() : 0);
        result = 31 * result + (readidualPayment != null ? readidualPayment.hashCode() : 0);
        result = 31 * result + (notes != null ? notes.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
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
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
