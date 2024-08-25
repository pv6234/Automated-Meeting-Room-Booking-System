package com.hsbc.beans;

import java.sql.Time;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Time;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TestMeeting {
    private Meeting meeting;

    @BeforeEach
    void setUp() {
        meeting = new Meeting();
    }

    @Test
    void testGenerateMeetingId() {
        int meetingId = meeting.generateMeetingId();
        assertEquals(1, meetingId);
    }

    @Test
    void testMeetingConstructor() {
        Meeting meeting = new Meeting(1, "Project Meeting", LocalDate.now(), new Time(10, 0, 0), 2, "Alice",
                101, "Business");
        assertEquals("Project Meeting", meeting.getMeetingTitle());
        assertEquals("Alice", meeting.getOrganizer());
    }

    @Test
    void testToString() {
        meeting.setMeetingId(1);
        meeting.setMeetingTitle("Project Meeting");
        assertEquals("Schedule{meetingId='1', meetingTitle='Project Meeting', date=null, durationInHours=0, creator='null', roomId='0', meetingType='null'}", meeting.toString());
    }
}
