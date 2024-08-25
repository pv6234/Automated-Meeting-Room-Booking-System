package com.hsbc.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestEmployeeDaoImpl {

    private EmployeeDaoImpl employeeDao;
    private Connection mockConn;
    private PreparedStatement mockPreparedStatement;

    @BeforeEach
    void setUp() throws Exception {
        mockConn = mock(Connection.class);
        employeeDao = new EmployeeDaoImpl();
        mockPreparedStatement = mock(PreparedStatement.class);
    }

    @Test
    void testGetEmployeeType() throws Exception {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mock(ResultSet.class));

        String employeeType = employeeDao.getEmployeeType("username");

        assertNull(employeeType);
        verify(mockConn, times(1)).prepareStatement(anyString());
    }
}
