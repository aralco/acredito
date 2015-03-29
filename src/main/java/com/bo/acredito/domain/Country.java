package com.bo.acredito.domain;

import javax.persistence.*;

/**
 * Created by aralco on 2/11/14.
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

}
