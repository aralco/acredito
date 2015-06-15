package com.bo.acredito.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private Long id;
    @Basic
    @NotNull
    @Column(name = "address1", nullable = false, insertable = true, updatable = true, length = 250)
    private String address1;
    @Basic
    @NotNull
    @Column(name = "address2", nullable = false, insertable = true, updatable = true, length = 250)
    private String address2;
    @Basic
    @NotNull
    @Column(name = "mobile", nullable = false, insertable = true, updatable = true, length = 45)
    private String mobile;
    @Basic
    @NotNull
    @Column(name = "phone", nullable = false, insertable = true, updatable = true, length = 45)
    private String phone;
    @Basic
    @NotNull
    @Column(name = "province", nullable = false, insertable = true, updatable = true, length = 45)
    private String province;
    @Basic
    @NotNull
    @Column(name = "workPhone", nullable = false, insertable = true, updatable = true, length = 45)
    private String workPhone;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "officeId", referencedColumnName = "id", nullable = false)
    private Office office;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "stateId", referencedColumnName = "id", nullable = false)
    private State state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
