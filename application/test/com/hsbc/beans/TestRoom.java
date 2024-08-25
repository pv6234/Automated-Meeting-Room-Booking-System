package com.hsbc.beans;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestRoom {
    private Room room;

    @BeforeEach
    void setUp() {
        room = new Room(1, "Conference Room", true, false, false, true, true, false, true, false, true, false);
    }

    @Test
    void testGenerateRoomId() {
        int roomId = room.generateRoomId();
        assertEquals(1000, roomId);
    }

    @Test
    void testRoomConstructor() {
        assertEquals("Conference Room", room.getName());
        assertTrue(room.isSeatingCapacityLessThanFive());
        assertFalse(room.isSeatingCapacityGreaterThanTen());
    }

    @Test
    void testToString() {
        assertEquals("Room{id=1000, name='Conference Room', seatingCapacityLessThanFive=true, seatingCapacityBetweenFiveAndTen=false, seatingCapacityGreaterThanTen=false, projector=true, wifiConnection=true, conferenceCallFacility=false, whiteboard=true, waterDispenser=false, TV=true, coffeeMachine=false}", room.toString());
    }
}

