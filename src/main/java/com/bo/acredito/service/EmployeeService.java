package com.bo.acredito.service;

import com.bo.acredito.domain.Employee;
import com.bo.acredito.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by aralco on 4/3/15.
 */
@Stateless
public class EmployeeService {

    @PersistenceContext(unitName = Constants.PERSISTENCE_UNIT)
    private EntityManager entityManager;

    public Employee findOne(long id)   {
        return entityManager.find(Employee.class, id);
    }

    public long exits(String username, String password) {
        Long id = (Long)entityManager.createQuery(
                "SELECT employee.id " +
                "FROM Employee employee " +
                "WHERE employee.username = :username "+
                "AND employee.password = :password")
                .setParameter("username", username)
                .setParameter("password", password)
                .getSingleResult();
        if(id==null)
            id = 0L;
        return id;
    }
}
