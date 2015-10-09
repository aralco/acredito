package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
@Table(name = "EMPLOYEE_ROLE", schema = "", catalog = "acredito")
@IdClass(EmployeeRolePK.class)
public class EmployeeRole {
    private String employeeId;
    private String roleId;
    private Employee employeeByEmployeeId;

    @Id
    @Column(name = "employeeId", nullable = false, insertable = false, updatable = false)
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Id
    @Column(name = "roleId", nullable = false, insertable = true, updatable = true)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeRole that = (EmployeeRole) o;

        if (employeeId != null ? !employeeId.equals(that.employeeId) : that.employeeId != null) return false;
        if (roleId != null ? !roleId.equals(that.roleId) : that.roleId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = employeeId != null ? employeeId.hashCode() : 0;
        result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
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
}
