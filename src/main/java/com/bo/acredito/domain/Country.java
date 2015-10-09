package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 6/14/15.
 */
@Entity
public class Country {
    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String id;
    @Basic
    @Column(name = "code", nullable = false, insertable = true, updatable = true, length = 2)
    private String code;
    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 100)
    private String name;
    @Basic
    @Column(name = "callCode", nullable = false, insertable = true, updatable = true)
    private Integer callCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

}
