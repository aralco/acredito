package com.bo.acredito.web;

//import com.bo.acredito.service.person.PersonService;
import com.bo.acredito.service.CustomerService;
import com.vaadin.server.VaadinServlet;

import javax.inject.Inject;
//
//import javax.inject.Inject;

/**
 * Created by asejas on 2/3/14.
 */
public class JEE6VaadinServlet extends VaadinServlet {

    @Inject
    private CustomerService customerService;

    public CustomerService getCustomerService() {
        return customerService;
    }
}
