package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

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
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Date date;
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
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "contactId1", referencedColumnName = "id", nullable = false)
    private Contact contact1=new Contact();
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "contactId2", referencedColumnName = "id", nullable = false)
    private Contact contact2=new Contact();
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "contactId3", referencedColumnName = "id", nullable = false)
    private Contact contact3=new Contact();


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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Contact getContact1() {
        return contact1;
    }

    public void setContact1(Contact contact1) {
        this.contact1 = contact1;
    }

    public Contact getContact2() {
        return contact2;
    }

    public void setContact2(Contact contact2) {
        this.contact2 = contact2;
    }

    public Contact getContact3() {
        return contact3;
    }

    public void setContact3(Contact contact3) {
        this.contact3 = contact3;
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
