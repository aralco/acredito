package com.bo.acredito.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by aralco on 2/1/14.
 */
@Entity
public class Employee {
    private long id;
    private long code;
    private boolean active;
    private String username;
    private String password;
    private Person personByPersonId;
    private Collection<Sale> salesById;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "code")
    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    @Basic
    @Column(name = "active")
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (active != employee.active) return false;
        if (code != employee.code) return false;
        if (id != employee.id) return false;
        if (password != null ? !password.equals(employee.password) : employee.password != null) return false;
        if (username != null ? !username.equals(employee.username) : employee.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (code ^ (code >>> 32));
        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "personId", referencedColumnName = "id", nullable = false)
    public Person getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }

    @OneToMany(mappedBy = "employeeByEmployeeId")
    public Collection<Sale> getSalesById() {
        return salesById;
    }

    public void setSalesById(Collection<Sale> salesById) {
        this.salesById = salesById;
    }
}
