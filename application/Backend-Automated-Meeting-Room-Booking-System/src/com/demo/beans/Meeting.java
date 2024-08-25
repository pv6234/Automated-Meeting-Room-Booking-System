package com.demo.beans;

import java.sql.Time;
import java.time.LocalDate;

public class Meeting {
    static int meetingCount=0;

    private int meetingId;
    private String meetingTitle;
    private LocalDate date;
    private Time startTime;
    private int durationInHours;  // Duration in hours
    private String creator;
    private int roomId;
    private String meetingType;

    public Meeting(){
    }

    public Meeting(int meetingId, String meetingTitle, LocalDate date, Time time, int durationInHours, String creator,
                    int roomId, String meetingType) {
        this.meetingId = generateMeetingId();
        this.meetingTitle = meetingTitle;
        this.date = date;
        this.startTime = time;
        this.durationInHours = durationInHours;
        this.creator = creator;
        this.roomId = roomId;
        this.meetingType = meetingType;
    }

    public int generateMeetingId() {
        meetingCount++;
        return meetingCount;
    }

    public int getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(int meetingId) {
        this.meetingId = meetingId;
    }

    public String getMeetingTitle() {
        return meetingTitle;
    }

    public void setMeetingTitle(String meetingTitle) {
        this.meetingTitle = meetingTitle;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public int getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(int durationInHours) {
        this.durationInHours = durationInHours;
    }

    public String getOrganizer() {
        return creator;
    }

    public void setOrganizer(String creator) {
        this.creator = creator;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getMeetingType() {
        return meetingType;
    }

    public void setMeetingType(String meetingType) {
        this.meetingType = meetingType;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "meetingId='" + meetingId + '\'' +
                ", meetingTitle='" + meetingTitle + '\'' +
                ", date=" + date +
                ", durationInHours=" + durationInHours +
                ", creator='" + creator + '\'' +
                ", roomId='" + roomId + '\'' +
                ", meetingType='" + meetingType + '\'' +
                '}';
    }
}
