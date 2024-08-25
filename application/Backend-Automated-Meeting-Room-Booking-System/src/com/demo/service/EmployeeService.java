package com.demo.service;

import com.demo.exceptions.EmployeeNotFoundException;

public interface EmployeeService {
    String getEmployeeType(String username) throws EmployeeNotFoundException;
}
