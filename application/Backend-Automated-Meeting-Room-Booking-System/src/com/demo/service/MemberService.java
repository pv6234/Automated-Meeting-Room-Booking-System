package com.demo.service;

import com.demo.beans.Meeting;
import com.demo.exceptions.NoMeetingsFoundException;

import java.util.List;

public interface MemberService {
    List<Meeting> viewMyMeetings(String username) throws NoMeetingsFoundException;
}
