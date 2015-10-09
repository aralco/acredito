package com.bo.acredito.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Payment {
    @Id
    @GeneratedValue(generator = "Payment")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String id;
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true)
    private Long code;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, insertable = true, updatable = true)
    private Date date;
    @Basic
    @Column(name = "chargedAmount", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double chargedAmount;
    @Basic
    @Column(name = "disbursedAmount", nullable = false, insertable = true, updatable = true, precision = 0)
    private Double disbursedAmount;
    @Basic
    @Column(name = "notes", nullable = false, insertable = true, updatable = true, length = 2147483647)
    private String notes;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "office_id", referencedColumnName = "id", nullable = false)
    private Office office;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Double getChargedAmount() {
        return chargedAmount;
    }

    public void setChargedAmount(Double chargedAmount) {
        this.chargedAmount = chargedAmount;
    }

    public Double getDisbursedAmount() {
        return disbursedAmount;
    }

    public void setDisbursedAmount(Double disbursedAmount) {
        this.disbursedAmount = disbursedAmount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employeeByEmployeeId) {
        this.employee = employeeByEmployeeId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerByCustomerId) {
        this.customer = customerByCustomerId;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office officeByOfficeId) {
        this.office = officeByOfficeId;
    }
}
