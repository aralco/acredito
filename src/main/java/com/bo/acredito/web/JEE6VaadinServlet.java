package com.bo.acredito.web;

import com.bo.acredito.service.*;
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

    @Inject
    private AuthService authService;

    @Inject
    private SupplierService supplierService;

    @Inject
    private PaymentPlanService paymentPlanService;

    public CustomerService getCustomerService() {
        return customerService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public SaleService getSaleService() {
        return saleService;
    }

    public AuthService getAuthService() { return authService; }
    public SupplierService getSupplierService() {
        return supplierService;
    }

    public PaymentPlanService getPaymentPlanService() {
        return paymentPlanService;
    }
}
