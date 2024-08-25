package com.hsbc.service;

import java.util.Collections;

import com.demo.beans.Meeting;
import com.demo.dao.MeetingDao;
import com.demo.exceptions.NoMeetingsFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

class TestMemberServiceImpl {

    private MemberServiceImpl memberService;
    private MeetingDao mockMeetingDao;

    @BeforeEach
    void setUp() {
        mockMeetingDao = mock(MeetingDao.class);
        memberService = new MemberServiceImpl();
        memberService.meetingDao = mockMeetingDao;
    }

    @Test
    void testViewMyMeetings_ValidCase() throws NoMeetingsFoundException {
        Meeting meeting = new Meeting(1, "Team Meeting", null, null, 1, "Bob", 1, "Business");
        when(mockMeetingDao.getMyMeetings("validUser")).thenReturn(Collections.singletonList(meeting));

        List<Meeting> meetings = memberService.viewMyMeetings("validUser");

        assertEquals(1, meetings.size());
        assertEquals("Team Meeting", meetings.get(0).getMeetingTitle());
        verify(mockMeetingDao, times(1)).getMyMeetings("validUser");
    }

    @Test
    void testViewMyMeetings_NoMeetingsFound() {
        when(mockMeetingDao.getMyMeetings("validUser")).thenThrow(new NoMeetingsFoundException("No meetings found"));

        assertThrows(NoMeetingsFoundException.class, () -> {
            memberService.viewMyMeetings("validUser");
        });

        verify(mockMeetingDao, times(1)).getMyMeetings("validUser");
    }
}

