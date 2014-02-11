package com.bo.acredito.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by aralco on 2/11/14.
 */
@Entity
public class Sale {
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
    @Column(name = "productPrice", nullable = false, insertable = true, updatable = true, length = 18, precision = 18, scale = 2)
    private Double productPrice;
    @Basic
    @Column(name = "discountedAmount", nullable = false, insertable = true, updatable = true, length = 18, precision = 18, scale = 2)
    private Double discountedAmount;
    @Basic
    @Column(name = "total", nullable = false, insertable = true, updatable = true, length = 18, precision = 18, scale = 2)
    private Double total;
    @Basic
    @Column(name = "saleType", nullable = false, insertable = true, updatable = true, length = 6, precision = 0)
    @Enumerated(EnumType.STRING)
    private SaleTypeEnum saleType;
    @Basic
    @Column(name = "initialPayment", nullable = false, insertable = true, updatable = true, length = 18, precision = 18, scale = 2)
    private Double initialPayment;
    @Basic
    @Column(name = "residualPayment", nullable = false, insertable = true, updatable = true, length = 18, precision = 18, scale = 2)
    private Double residualPayment;
    @Basic
    @Column(name = "paymentQuotes", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private Integer paymentQuotes;
    @Basic
    @Lob
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 65535, precision = 0)
    private String notes;
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    private Product product;

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

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public SaleTypeEnum getSaleType() {
        return saleType;
    }

    public void setSaleType(SaleTypeEnum saleType) {
        this.saleType = saleType;
    }

    public Double getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(Double initialPayment) {
        this.initialPayment = initialPayment;
    }

    public Double getResidualPayment() {
        return residualPayment;
    }

    public void setResidualPayment(Double residualPayment) {
        this.residualPayment = residualPayment;
    }

    public Integer getPaymentQuotes() {
        return paymentQuotes;
    }

    public void setPaymentQuotes(Integer paymentQuotes) {
        this.paymentQuotes = paymentQuotes;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
