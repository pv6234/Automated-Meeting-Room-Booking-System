package com.demo.service;

import com.demo.beans.Meeting;
import com.demo.beans.Room;
import com.demo.dao.*;
import com.demo.exceptions.InsufficientCreditsException;
import com.demo.exceptions.NoMeetingsFoundException;
import com.demo.exceptions.RoomNotAvailableException;
import com.demo.exceptions.RoomNotFoundException;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class ManagerServiceImpl implements ManagerService{
    private Meeting meeting;
    private MeetingDao meetingDao;
    private EmployeeDao employeeDao;
    private ManagerCreditsDao managerCreditsDao;
    private RoomDao roomDao;
    Scanner sc = new Scanner(System.in);

    public ManagerServiceImpl(){
        roomDao = StorageFactory.getInstance();
    }

    @Override
    public List<Room> fetchEligibleRooms(String username) throws RoomNotFoundException {
        int choice=0;
        choice=sc.nextInt();
        System.out.println("select the meeting type: ");
        System.out.println("1) classroomTraining");
        System.out.println("2) onlineTraining");
        System.out.println("3) conferenceCall");
        System.out.println("4) business");
        String meetType=sc.next();

        return roomDao.getRooms(meetType);
    }

    @Override
    public void bookRoom(String username) throws InsufficientCreditsException, RoomNotAvailableException {
        System.out.println("Enter the room name which you want to book: ");
        String roomName=sc.next();
        int hourlyRoomCredits=roomDao.getHourlyRoomCredits(roomName);
        int mgrCredits=managerCreditsDao.getMgrCredits(username);

        System.out.println("Enter the duration of your meeting (in Hours): ");
        int durationInHours = Integer.parseInt(sc.nextLine());

        if(mgrCredits>hourlyRoomCredits*durationInHours){
            int meetingId = meeting.generateMeetingId();

            System.out.println("Enter Meeting Title: ");
            String meetingTitle = sc.nextLine();

            System.out.println("Enter Meeting Date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(sc.nextLine());

            System.out.println("Enter Meeting Time: ");
            Time time = Time.valueOf(sc.nextLine());

            System.out.println("Enter Organizer Name: ");
            String creator = sc.nextLine();

            System.out.println("Enter Room ID: ");
            int roomId = sc.nextInt();

            System.out.println("Enter Meeting Type: ");
            String meetingType = sc.nextLine();

            Meeting newMeeting = new Meeting(meetingId, meetingTitle, date, time, durationInHours, creator, roomId, meetingType);

            roomDao.saveSchedule(newMeeting);

            mgrCredits-=hourlyRoomCredits*durationInHours;
            managerCreditsDao.updateCredits(mgrCredits,username);
        }else{
            throw new InsufficientCreditsException("You don't have sufficient credits to book this room for the desired duration");
        }
    }

    @Override
    public List<Meeting> viewMeetings() throws NoMeetingsFoundException {
        return meetingDao.getAllMeetings();
    }
}
