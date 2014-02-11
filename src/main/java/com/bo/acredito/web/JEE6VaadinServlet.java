package com.bo.acredito.web;

import com.bo.acredito.service.CustomerService;
import com.bo.acredito.service.SaleService;
import com.vaadin.server.VaadinServlet;

import javax.inject.Inject;

/**
 * Created by asejas on 2/3/14.
 */
public class JEE6VaadinServlet extends VaadinServlet {

    @Inject
    private CustomerService customerService;

    @Inject
    private SaleService saleService;

    public CustomerService getCustomerService() {
        return customerService;
    }

    public SaleService getSaleService() {
        return saleService;
    }
}
