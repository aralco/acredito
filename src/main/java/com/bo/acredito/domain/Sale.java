package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;
    @Version
    @Column(name = "version", nullable = false, insertable = true, updatable = true)
    private Long version;
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true)
    private Long code;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, insertable = true, updatable = true)
    private Date date;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "saleType", nullable = false, insertable = true, updatable = true, length = 50)
    private SaleType saleType;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "saleStatus", nullable = false, insertable = true, updatable = true, length = 50)
    private SaleStatus saleStatus;
    @Basic
    @Column(name = "partialAmount", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double partialAmount;
    @Basic
    @Column(name = "discount", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double discount;
    @Basic
    @Column(name = "totalAmount", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double totalAmount;
    @Basic
    @Column(name = "advanceAmount", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double advanceAmount;
    @Basic
    @Column(name = "residualPayment", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double residualPayment;
    @Basic
    @Column(name = "initialPayment", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double initialPayment;
    @Basic
    @Column(name = "paymentQuotes", nullable = false, insertable = true, updatable = true)
    private Integer paymentQuotes;
    @Basic
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 65535)
    private String notes;
    @Basic
    @Column(name = "delivered", nullable = false, insertable = true, updatable = true)
    private Boolean delivered;
    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactId1", referencedColumnName = "id")
    private Contact contact1;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactId2", referencedColumnName = "id")
    private Contact contact2;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contactId3", referencedColumnName = "id")
    private Contact contact3;
    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id", nullable = false)
    private Employee employee;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;
    @ManyToOne
    @JoinColumn(name = "paymentPlanId", referencedColumnName = "id")
    private PaymentPlan paymentPlan;

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

    public SaleType getSaleType() {
        return saleType;
    }

    public void setSaleType(SaleType saleType) {
        this.saleType = saleType;
    }

    public SaleStatus getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(SaleStatus saleStatus) {
        this.saleStatus = saleStatus;
    }

    public Double getPartialAmount() {
        return partialAmount;
    }

    public void setPartialAmount(Double partialAmount) {
        this.partialAmount = partialAmount;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getAdvanceAmount() {
        return advanceAmount;
    }

    public void setAdvanceAmount(Double advanceAmount) {
        this.advanceAmount = advanceAmount;
    }

    public Double getResidualPayment() {
        return residualPayment;
    }

    public void setResidualPayment(Double residualPayment) {
        this.residualPayment = residualPayment;
    }

    public Double getInitialPayment() {
        return initialPayment;
    }

    public void setInitialPayment(Double initialPayment) {
        this.initialPayment = initialPayment;
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

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public PaymentPlan getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(PaymentPlan paymentPlan) {
        this.paymentPlan = paymentPlan;
    }
}
