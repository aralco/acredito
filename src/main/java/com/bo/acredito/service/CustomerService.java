package com.bo.acredito.service;

import com.bo.acredito.domain.Customer;
import com.bo.acredito.domain.Occupation;
import com.bo.acredito.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class implements the persistence Business logic
 * Created by asejas on 2/8/14.
 */
@Stateless
public class CustomerService {
    @PersistenceContext(unitName = Constants.PERSISTENCE_UNIT)
    private EntityManager em;

    public void saveCustomer(Customer customer){
        Long code= (Long) em.createQuery("SELECT MAX(customer.code) FROM Customer customer").getSingleResult();
        if(code==null){
            code=0L;
        }
        customer.setCode(code+1);
        //TODO This line fails when saving a new customer
        em.persist(customer);
    }

    public void updateCustomer(Customer customer){
        em.merge(customer);
    }

    public void saveOccupation(Occupation occupation){
        em.persist(occupation);
    }

}
