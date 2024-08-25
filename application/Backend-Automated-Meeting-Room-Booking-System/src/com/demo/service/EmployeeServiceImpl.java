package com.demo.service;

import com.demo.dao.EmployeeDao;
import com.demo.exceptions.EmployeeNotFoundException;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDao employeeDao;

    @Override
    public String getEmployeeType(String username) throws EmployeeNotFoundException {
        String employeeType = employeeDao.getEmployeeType(username);

        if (employeeType == null) {
            throw new EmployeeNotFoundException("Employee with username '" + username + "' not found.");
        }

        return employeeType;
    }
}
