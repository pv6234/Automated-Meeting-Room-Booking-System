package com.hsbc.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TestEmployee {
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
    }

    @Test
    void testGenerateEmployeeId() {
        int empId = employee.generateEmployeeId();
        assertEquals(100, empId);
    }

    @Test
    void testEmployeeConstructor() {
        Employee employee = new Employee(1, "John Doe", "1234567890", "john@example.com",
                "IT", "Developer", new Date(), "Male");
        assertEquals("John Doe", employee.getEname());
        assertEquals("1234567890", employee.getPhone());
        assertEquals("john@example.com", employee.getEmail());
    }

    @Test
    void testToString() {
        employee.setEid(1);
        employee.setEname("John Doe");
        assertEquals("Employee{eid=1, ename='John Doe', phone='null', email='null', dept='null', desg='null', dob=null, gender='null'}", employee.toString());
    }
}
