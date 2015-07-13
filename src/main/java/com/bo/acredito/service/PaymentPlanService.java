package com.bo.acredito.service;

import com.bo.acredito.domain.PaymentPlan;
import com.bo.acredito.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author aralco
 */
@Stateless
public class PaymentPlanService {
    @PersistenceContext(unitName = Constants.PERSISTENCE_UNIT)
    private EntityManager em;


    public void save(PaymentPlan paymentPlan)   {
        em.persist(paymentPlan);
    }

    public void update(PaymentPlan paymentPlan)   {
        em.merge(paymentPlan);
    }
}
