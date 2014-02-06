package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 2/5/14.
 */
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long id;
    @Version
    @Column(name = "version", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long version;
    @Basic
    @Column(name = "address1", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
    private String address1;
    @Basic
    @Column(name = "address2", nullable = false, insertable = true, updatable = true, length = 250, precision = 0)
    private String address2;
    @Basic
    @Column(name = "province", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String province;
    @Basic
    @Column(name = "phone", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String phone;
    @Basic
    @Column(name = "mobile", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String mobile;
    @Basic
    @Column(name = "workPhone", nullable = false, insertable = true, updatable = true, length = 45, precision = 0)
    private String workPhone;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (address1 != null ? !address1.equals(address.address1) : address.address1 != null) return false;
        if (address2 != null ? !address2.equals(address.address2) : address.address2 != null) return false;
        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (mobile != null ? !mobile.equals(address.mobile) : address.mobile != null) return false;
        if (phone != null ? !phone.equals(address.phone) : address.phone != null) return false;
        if (province != null ? !province.equals(address.province) : address.province != null) return false;
        if (version != null ? !version.equals(address.version) : address.version != null) return false;
        if (workPhone != null ? !workPhone.equals(address.workPhone) : address.workPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (address1 != null ? address1.hashCode() : 0);
        result = 31 * result + (address2 != null ? address2.hashCode() : 0);
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        return result;
    }
}
