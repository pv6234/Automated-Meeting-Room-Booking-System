package com.hsbc.service;

import java.util.Collections;

import com.demo.beans.Meeting;
import com.demo.beans.Room;
import com.demo.dao.*;
        import com.demo.exceptions.InsufficientCreditsException;
import com.demo.exceptions.NoMeetingsFoundException;
import com.demo.exceptions.RoomNotAvailableException;
import com.demo.exceptions.RoomNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class TestManagerServiceImpl {

    private ManagerServiceImpl managerService;
    private MeetingDao mockMeetingDao;
    private EmployeeDao mockEmployeeDao;
    private ManagerCreditsDao mockManagerCreditsDao;
    private RoomDao mockRoomDao;

    @BeforeEach
    void setUp() {
        mockMeetingDao = mock(MeetingDao.class);
        mockEmployeeDao = mock(EmployeeDao.class);
        mockManagerCreditsDao = mock(ManagerCreditsDao.class);
        mockRoomDao = mock(RoomDao.class);

        managerService = new ManagerServiceImpl();
        managerService.meetingDao = mockMeetingDao;
        managerService.employeeDao = mockEmployeeDao;
        managerService.managerCreditsDao = mockManagerCreditsDao;
        managerService.roomDao = mockRoomDao;
    }

    @Test
    void testFetchEligibleRooms_ValidType() throws RoomNotFoundException {
        Room room = new Room(1, "Conference Room", true, false, true, true, true, false, true, false, true, false);
        when(mockRoomDao.getRooms("conferenceCall")).thenReturn(Collections.singletonList(room));

        List<Room> rooms = managerService.fetchEligibleRooms("conferenceCall");

        assertEquals(1, rooms.size());
        assertEquals("Conference Room", rooms.get(0).getName());
        verify(mockRoomDao, times(1)).getRooms("conferenceCall");
    }

    @Test
    void testFetchEligibleRooms_InvalidType() {
        when(mockRoomDao.getRooms("invalidType")).thenThrow(new RoomNotFoundException("Room not found"));

        assertThrows(RoomNotFoundException.class, () -> {
            managerService.fetchEligibleRooms("invalidType");
        });

        verify(mockRoomDao, times(1)).getRooms("invalidType");
    }

    @Test
    void testBookRoom_ValidBooking() throws InsufficientCreditsException, RoomNotAvailableException {
        when(mockRoomDao.getHourlyRoomCredits("Conference Room")).thenReturn(50);
        when(mockManagerCreditsDao.getMgrCredits("managerUser")).thenReturn(200);

        assertDoesNotThrow(() -> {
            managerService.bookRoom("managerUser");
        });

        verify(mockRoomDao, times(1)).saveSchedule(any(Meeting.class));
        verify(mockManagerCreditsDao, times(1)).updateCredits(anyInt(), eq("managerUser"));
    }

    @Test
    void testBookRoom_InsufficientCredits() {
        when(mockRoomDao.getHourlyRoomCredits("Conference Room")).thenReturn(50);
        when(mockManagerCreditsDao.getMgrCredits("managerUser")).thenReturn(20);

        assertThrows(InsufficientCreditsException.class, () -> {
            managerService.bookRoom("managerUser");
        });

        verify(mockRoomDao, never()).saveSchedule(any(Meeting.class));
    }

    @Test
    void testViewMeetings_ValidCase() throws NoMeetingsFoundException {
        Meeting meeting = new Meeting(1, "Project Meeting", null, null, 1, "Alice", 1, "Business");
        when(mockMeetingDao.getAllMeetings()).thenReturn(Collections.singletonList(meeting));

        List<Meeting> meetings = managerService.viewMeetings();

        assertEquals(1, meetings.size());
        assertEquals("Project Meeting", meetings.get(0).getMeetingTitle());
        verify(mockMeetingDao, times(1)).getAllMeetings();
    }

    @Test
    void testViewMeetings_NoMeetingsFound() {
        when(mockMeetingDao.getAllMeetings()).thenThrow(new NoMeetingsFoundException("No meetings found"));

        assertThrows(NoMeetingsFoundException.class, () -> {
            managerService.viewMeetings();
        });

        verify(mockMeetingDao, times(1)).getAllMeetings();
    }
}
