package com.bo.acredito.ui.forms;

import com.bo.acredito.MyVaadinUI;
import com.bo.acredito.domain.Employee;
import com.bo.acredito.service.AuthService;
import com.bo.acredito.web.JEE6VaadinServlet;
import com.ejt.vaadin.loginform.DefaultVerticalLoginForm;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * Created by aralco on 3/31/15.
 */
public class LoginForm extends DefaultVerticalLoginForm {

    @Override
    protected void login(String username, String password) {
        System.out.println("Login form: -->user:"+username+", pwd:"+password);
        //TODO add security level
        AuthService authService=((JEE6VaadinServlet) VaadinServlet.getCurrent()).getAuthService();
        Employee employee = authService.authenticate(username, password);

        ((MyVaadinUI) UI.getCurrent()).setEmployee(employee);
        ((MyVaadinUI) UI.getCurrent()).buildUI();
    }
}
