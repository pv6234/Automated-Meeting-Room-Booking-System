package com.hsbc.dao;

import java.sql.PreparedStatement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class TestRoomDaoImpl {

    private RoomDaoImpl roomDao;
    private Connection mockConn;
    private PreparedStatement mockPreparedStatement;

    @BeforeEach
    void setUp() throws Exception {
        mockConn = mock(Connection.class);
        roomDao = new RoomDaoImpl();
        mockPreparedStatement = mock(PreparedStatement.class);
    }

    @Test
    void testSaveRoom() throws Exception {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        assertDoesNotThrow(() -> roomDao.save(new Room(1, "Room1", true, false, true, true, true, false, true, false, true, false)));

        verify(mockConn, times(1)).prepareStatement(anyString());
    }

    @Test
    void testGetRooms() throws Exception {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStatement);

        List<Room> rooms = roomDao.getRooms("conferenceCall");

        assertNotNull(rooms);
        verify(mockConn, times(1)).prepareStatement(anyString());
    }
}
