package com.bo.acredito.service;

import com.bo.acredito.domain.Payment;
import com.bo.acredito.domain.Sale;
import com.bo.acredito.domain.SaleProduct;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by aralco on 2/11/14.
 */
@Stateless
public class SaleService {
    EntityManager entityManager = JPAContainerFactory.createEntityManagerForPersistenceUnit(Constants.PERSISTENCE_UNIT);

    public void savePayments(List<Payment> payments)    {
        for(Payment p : payments)   {
            entityManager.persist(p);
        }
    }

    public void saveSale(Sale sale) {
        entityManager.persist(sale);
    }

    public List<SaleProduct> loadSaleProducts(Long saleId){
        TypedQuery<SaleProduct> query =
                entityManager.createNamedQuery("SaleProduct.findBySaleId", SaleProduct.class);
        return query.setParameter("saleId",saleId).getResultList();
    }
}
