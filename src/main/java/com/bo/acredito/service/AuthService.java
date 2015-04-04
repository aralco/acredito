package com.bo.acredito.service;

import com.bo.acredito.domain.Employee;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by aralco on 4/3/15.
 */
@Stateless
public class AuthService {

    @Inject
    private EmployeeService employeeService;

    public Employee authenticate(String username, String password) {
        Employee employee = null;
        long employeeId = employeeService.exits(username, password);
        if(employeeId!=0)  {
            employee = employeeService.findOne(employeeId);
        }
        return employee;
    }
}
