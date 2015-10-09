package com.bo.acredito.service;

import com.bo.acredito.domain.Product;
import com.bo.acredito.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class implements the persistence Business logic
 * Created by asejas on 29/3/15.
 */
@Stateless
public class ProductService {
    @PersistenceContext(unitName = Constants.PERSISTENCE_UNIT)
    private EntityManager em;

    public void saveProduct(Product product){
        Long code= (Long) em.createQuery("SELECT MAX(product.code) FROM Product product").getSingleResult();
        if(code==null){
            code=0L;
        }
        product.setCode(code+1);
        em.persist(product);
    }
    public void updateProduct(Product product){
        em.merge(product);
    }

}
