package com.hsbc.dao;

import java.sql.PreparedStatement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class TestManagerCreditsDaoImpl {

    private ManagerCreditsDaoImpl managerCreditsDao;
    private Connection mockConn;
    private PreparedStatement mockPreparedStatement;

    @BeforeEach
    void setUp() throws Exception {
        mockConn = mock(Connection.class);
        managerCreditsDao = new ManagerCreditsDaoImpl();
        mockPreparedStatement = mock(PreparedStatement.class);
    }

    @Test
    void testResetAllManagerCredits() throws Exception {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertDoesNotThrow(() -> managerCreditsDao.resetAllManagerCredits(1000));

        verify(mockConn, times(1)).prepareStatement(anyString());
    }

    @Test
    void testUpdateCredits() throws Exception {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertDoesNotThrow(() -> managerCreditsDao.updateCredits(1000, "username"));

        verify(mockConn, times(1)).prepareStatement(anyString());
    }

    @Test
    void testGetMgrCredits() throws Exception {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        int credits = managerCreditsDao.getMgrCredits("username");

        assertEquals(0, credits);
        verify(mockConn, times(1)).prepareStatement(anyString());
    }
}

