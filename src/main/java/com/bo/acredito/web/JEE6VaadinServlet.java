package com.bo.acredito.web;

import com.bo.acredito.service.CustomerService;
import com.bo.acredito.service.ProductService;
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

    @Inject
    private ProductService productService;

    public CustomerService getCustomerService() {
        return customerService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public SaleService getSaleService() {
        return saleService;
    }
}
