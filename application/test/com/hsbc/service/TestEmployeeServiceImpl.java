package com.hsbc.service;

import com.demo.dao.EmployeeDao;
import com.demo.exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class TestEmployeeServiceImpl {

    private EmployeeServiceImpl employeeService;
    private EmployeeDao mockEmployeeDao;

    @BeforeEach
    void setUp() {
        mockEmployeeDao = mock(EmployeeDao.class);
        employeeService = new EmployeeServiceImpl();
        employeeService.employeeDao = mockEmployeeDao;  // Set the mock DAO
    }

    @Test
    void testGetEmployeeType_ValidUser() throws EmployeeNotFoundException {
        when(mockEmployeeDao.getEmployeeType("validUser")).thenReturn("Manager");

        String employeeType = employeeService.getEmployeeType("validUser");

        assertEquals("Manager", employeeType);
        verify(mockEmployeeDao, times(1)).getEmployeeType("validUser");
    }

    @Test
    void testGetEmployeeType_UserNotFound() {
        when(mockEmployeeDao.getEmployeeType("invalidUser")).thenReturn(null);

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getEmployeeType("invalidUser");
        });

        verify(mockEmployeeDao, times(1)).getEmployeeType("invalidUser");
    }
}
