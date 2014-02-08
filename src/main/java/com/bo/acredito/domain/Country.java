package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 2/8/14.
 */
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    private Long id;
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 2, precision = 0)
    private String code;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 100, precision = 0)
    private String name;
    @Basic
    @Column(name = "callCode", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private Integer callCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCallCode() {
        return callCode;
    }

    public void setCallCode(Integer callCode) {
        this.callCode = callCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (callCode != null ? !callCode.equals(country.callCode) : country.callCode != null) return false;
        if (code != null ? !code.equals(country.code) : country.code != null) return false;
        if (id != null ? !id.equals(country.id) : country.id != null) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (callCode != null ? callCode.hashCode() : 0);
        return result;
    }
}
