package com.demo.service;

import com.demo.beans.Meeting;
import com.demo.dao.MeetingDao;
import com.demo.exceptions.NoMeetingsFoundException;

import java.util.List;

public class MemberServiceImpl implements MemberService{
    private MeetingDao meetingDao;
    @Override
    public List<Meeting> viewMyMeetings(String username) throws NoMeetingsFoundException {
        return meetingDao.getMyMeetings(username);
    }
}
