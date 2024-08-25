package com.demo.dao;

import com.demo.beans.Meeting;
import com.demo.exceptions.DatabaseConnectionException;
import com.demo.exceptions.NoMeetingsFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeetingDaoImpl implements MeetingDao {
    private Connection conn;

    public MeetingDaoImpl() {
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Failed to establish a database connection.", e);
        }
    }


    @Override
    public List<Meeting> getAllMeetings() throws NoMeetingsFoundException {
        List<Meeting> meetings = new ArrayList<>();
        String query = "SELECT * FROM Meeting";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Meeting meeting = new Meeting();
                meeting.setMeetingId(rs.getInt("MeetingId"));
                meeting.setMeetingTitle(rs.getString("MeetingTitle"));
                meeting.setDate(rs.getDate("MeetingDate").toLocalDate());
                meeting.setStartTime(rs.getTime("StartTime"));
                meeting.setDurationInHours(rs.getInt("DurationInHours"));
                meeting.setOrganizer(rs.getString("Organizer"));
                meeting.setRoomId(rs.getInt("RoomId"));
                meeting.setMeetingType(rs.getString("MeetingType"));
                meetings.add(meeting);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(meetings == null || meetings.isEmpty()) {
            throw new NoMeetingsFoundException("No meetings found.");
        }
        return meetings;
    }

    @Override
    public List<Meeting> getMyMeetings(String username) throws NoMeetingsFoundException {
        List<Meeting> meetings = new ArrayList<>();
        List<Integer> meetingIds = new ArrayList<>();

        // Query to fetch meeting IDs from InviteeMeeting table based on ename
        String query1 = "SELECT meetingId FROM InviteeMeeting WHERE ename = ?";

        try (PreparedStatement pstmt1 = conn.prepareStatement(query1)) {
            pstmt1.setString(1, username);
            try (ResultSet rs1 = pstmt1.executeQuery()) {
                while (rs1.next()) {
                    meetingIds.add(rs1.getInt("meetingId"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (meetingIds.isEmpty()) {
            // No meetings found for this employee
            throw new NoMeetingsFoundException("You don't have any meetings currently scheduled.");
        }

        // Query to fetch meeting details based on meeting IDs
        String query2 = "SELECT * FROM Meeting WHERE MeetingID = ?";

        try (PreparedStatement pstmt2 = conn.prepareStatement(query2)) {
            for (int meetingId : meetingIds) {
                pstmt2.setInt(1, meetingId);
                try (ResultSet rs2 = pstmt2.executeQuery()) {
                    if (rs2.next()) {
                        Meeting meeting = new Meeting();
                        meeting.setMeetingId(rs2.getInt("MeetingId"));
                        meeting.setMeetingTitle(rs2.getString("MeetingTitle"));
                        meeting.setDate(rs2.getDate("MeetingDate").toLocalDate());
                        meeting.setStartTime(rs2.getTime("StartTime"));
                        meeting.setDurationInHours(rs2.getInt("DurationInHours"));
                        meeting.setOrganizer(rs2.getString("Organizer"));
                        meeting.setRoomId(rs2.getInt("RoomId"));
                        meeting.setMeetingType(rs2.getString("MeetingType"));
                        meetings.add(meeting);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return meetings;
    }
}
