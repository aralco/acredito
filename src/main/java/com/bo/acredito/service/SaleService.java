package com.bo.acredito.service;

import com.bo.acredito.domain.*;
import com.bo.acredito.util.Constants;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

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

    public void createSale(Sale sale, List<Product> products) {
        Long code= (Long) entityManager.createQuery("SELECT MAX(sale.code)+1 FROM Sale sale").getSingleResult();
        if(code==null){
            code=1000L;
        }
        sale.setCode(code);
        sale.setDate(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        sale.setSaleStatus(SaleStatus.NOT_PAID);
        sale.setDelivered(false);
        entityManager.persist(sale);

        for(Product product : products) {
            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setQuantity(1);
            saleProduct.setUnitPrice(product.getPrice());
            saleProduct.setPartialAmount(sale.getPartialAmount());
            saleProduct.setSale(sale);
            saleProduct.setProduct(product);
            saleProduct.setOffice(sale.getOffice());
            sale.getSaleProducts().add(saleProduct);
            entityManager.persist(saleProduct);
        }
    }

}
