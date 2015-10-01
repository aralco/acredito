package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class TestEntity {
    private String id;

    private String code;

    @Id
    @GeneratedValue(generator = "prefix-custom")
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    @Column(name = "code", nullable = false, insertable = true, updatable = true)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestEntity role = (TestEntity) o;

        if (code != null ? !code.equals(role.code) : role.code != null) return false;
        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }
}
