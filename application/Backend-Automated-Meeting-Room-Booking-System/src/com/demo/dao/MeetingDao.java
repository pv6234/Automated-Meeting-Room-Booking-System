package com.demo.dao;

import com.demo.beans.Meeting;
import com.demo.exceptions.NoMeetingsFoundException;

import java.util.List;

public interface MeetingDao {
    List<Meeting> getAllMeetings() throws NoMeetingsFoundException;

    List<Meeting> getMyMeetings(String username) throws NoMeetingsFoundException;
}
