package com.hsbc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class TestAdminServiceImpl {

    private AdminServiceImpl adminService;
    private RoomDao mockRoomDao;

    @BeforeEach
    void setUp() {
        mockRoomDao = mock(RoomDao.class);
        adminService = new AdminServiceImpl();
    }

    @Test
    void testAddNewRoom() {
        // You can create mocks for user input and room creation
        adminService.addNewRoom();

        verify(mockRoomDao, times(1)).save(any(Room.class));
    }

    @Test
    void testEditRoom() {
        // You can create mocks for user input and room editing
        adminService.editRoom();

        verify(mockRoomDao, times(1)).changeRoomName(anyString(), anyString());
    }
}
