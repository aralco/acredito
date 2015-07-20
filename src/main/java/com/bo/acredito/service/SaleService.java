package com.bo.acredito.service;

import com.bo.acredito.domain.*;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;
import java.util.Calendar;

/**
 * @author aralco
 */
@Stateless
public class SaleService {
    EntityManager entityManager = JPAContainerFactory.createEntityManagerForPersistenceUnit(Constants.PERSISTENCE_UNIT);

    public void savePayments(List<Payment> payments)    {
        for(Payment p : payments)   {
            entityManager.persist(p);
        }
    }

    public Sale createSale(Sale sale) {
        Long code= (Long) entityManager.createQuery("SELECT MAX(sale.code)+1 FROM Sale sale").getSingleResult();
        if(code==null){
            code=1000L;
        }
        sale.setCode(code);
        sale.setDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        sale.setSaleStatus(SaleStatus.NOT_PAID);
        sale.setDelivered(false);
        entityManager.persist(sale);
        return sale;
    }

    public void createSaleProduct(Sale sale, List<Product> products) {
        System.out.println(sale);
        for(Product product : products) {
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setQuantity(1);
            saleProduct.setUnitPrice(product.getPrice());
            saleProduct.setPartialAmount(sale.getPartialAmount());
            saleProduct.setSale(sale);
            saleProduct.setProduct(product);
            saleProduct.setOffice(sale.getOffice());
            entityManager.persist(saleProduct);
        }
        entityManager.flush();
    }

    public List<SaleProduct> loadSaleProducts(Long saleId){
        TypedQuery<SaleProduct> query =
                entityManager.createNamedQuery("SaleProduct.findBySaleId", SaleProduct.class);
        return query.setParameter("saleId",saleId).getResultList();
    }
}
