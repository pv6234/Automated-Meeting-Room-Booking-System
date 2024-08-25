package com.demo.service;

import com.demo.beans.Meeting;
import com.demo.beans.Room;
import com.demo.exceptions.InsufficientCreditsException;
import com.demo.exceptions.NoMeetingsFoundException;
import com.demo.exceptions.RoomNotAvailableException;
import com.demo.exceptions.RoomNotFoundException;

import java.util.List;

public interface ManagerService {
    List<Room> fetchEligibleRooms(String username) throws RoomNotFoundException;

    void bookRoom(String username) throws InsufficientCreditsException, RoomNotAvailableException;

    List<Meeting> viewMeetings() throws NoMeetingsFoundException;
}
