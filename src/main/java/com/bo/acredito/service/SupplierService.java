package com.bo.acredito.service;

import com.bo.acredito.domain.Product;
import com.bo.acredito.domain.Supplier;
import com.bo.acredito.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Alvaro on 4/17/2015.
 */
@Stateless
public class SupplierService {
    @PersistenceContext(unitName = Constants.PERSISTENCE_UNIT)
    private EntityManager em;

    public void saveSupplier(Supplier supplier){
        Long code= (Long) em.createQuery("SELECT MAX(supplier.code) FROM Supplier supplier").getSingleResult();
        if(code==null){
            code=0L;
        }
        supplier.setCode(code+1);
        em.persist(supplier);
    }

    public void updateSupplier(Supplier supplier){
        em.merge(supplier);
    }
}
