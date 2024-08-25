package com.demo.dao;

import com.demo.beans.Meeting;
import com.demo.beans.Room;
import com.demo.exceptions.RoomNotAvailableException;
import com.demo.exceptions.RoomNotFoundException;

import java.util.List;

public interface RoomDao {
    void deleteRoom(String name);

    void changeRoomName(String name, String newName);

    void save(Room newRoom);

    void changeAmenitiesForARoom(String name,
                                boolean seatingCapacityLessThanFive,
                                boolean seatingCapacityBetweenFiveAndTen,
                                boolean seatingCapacityGreaterThanTen,
                                boolean projector,
                                boolean wifiConnection,
                                boolean conferenceCallFacility,
                                boolean whiteboard,
                                boolean waterDispenser,
                                boolean tv,
                                boolean coffeeMachine);

    List<Room> getRooms(String meetType) throws RoomNotFoundException;

    int getHourlyRoomCredits(String roomName);

    void saveSchedule(Meeting newMeeting) throws RoomNotAvailableException;
}
