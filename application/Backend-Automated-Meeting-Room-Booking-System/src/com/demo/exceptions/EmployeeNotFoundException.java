package com.demo.exceptions;

// Exception thrown when an employee is not found in the database
public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}