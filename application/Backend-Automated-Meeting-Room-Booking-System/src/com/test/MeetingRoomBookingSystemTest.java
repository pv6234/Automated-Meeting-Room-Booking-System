package com.demo.test;

import com.demo.beans.Meeting;
import com.demo.beans.Room;
import com.demo.exceptions.*;
import com.demo.service.*;

import java.util.Scanner;
import java.util.List;

public class MeetingRoomBookingSystemTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeService employeeService = new EmployeeServiceImpl();

        System.out.println("Welcome to the Automated Meeting Room Booking System");
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();

        // Fetching the user type from the service layer
        String userType = null;
        try {
            userType = employeeService.getEmployeeType(username);
        } catch (EmployeeNotFoundException e) {
            e.printStackTrace();
        }

//        if (userType == null) {
//            System.out.println("Invalid username. Please try again.");
//            return;
//        }

        System.out.println("User type identified as: " + userType);

        // Switch statement based on the user type
        switch (userType.toLowerCase()) {
            case "admin":
                AdminService adminService = new AdminServiceImpl();
                System.out.println("Admin Menu:");
                System.out.println("1. Create a Room");
                System.out.println("2. Configure an existing room");

                System.out.print("Enter your choice: ");
                int adminChoice = scanner.nextInt();
                // Handle admin choices here
                switch (adminChoice) {
                    case 1:
                        adminService.addNewRoom();
                        break;
                    case 2:
                        adminService.editRoom();
                        break;
                    default:
                        System.out.println("Invalid choice. Exiting.");
                        break;
                }
                break;

            case "manager":
                ManagerService managerService = new ManagerServiceImpl();
                System.out.println("Manager Menu:");
                System.out.println("1. View Meetings");
                System.out.println("2. Book a Meeting Room");

                System.out.print("Enter your choice: ");
                int managerChoice = scanner.nextInt();
                // Handle manager choices here
                switch (managerChoice) {
                    case 1:
                        try {
                            List<Meeting> schedule = managerService.viewMeetings();
                        } catch (NoMeetingsFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        List<Room> eligibleRooms = null;
                        try {
                            eligibleRooms = managerService.fetchEligibleRooms(username);
                        } catch (RoomNotFoundException e) {
                            e.printStackTrace();
                        }
                        System.out.println("Rooms found: " + eligibleRooms.size());
                        System.out.println("Eligible rooms:");
                        for (Room room : eligibleRooms) {
                            System.out.println(room);
                        }
                        try {
                            managerService.bookRoom(username);
                        } catch (InsufficientCreditsException e) {
                            e.printStackTrace();
                        } catch (RoomNotAvailableException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Exiting.");
                        break;
                }
                break;

            case "member":
                MemberService memberService = new MemberServiceImpl();
                System.out.println("Member Menu:");
                System.out.println("1. View My Meetings");

                System.out.print("Enter your choice: ");
                int memberChoice = scanner.nextInt();
                // Handle member choices here
                switch (memberChoice) {
                    case 1:
                        System.out.println("Viewing your bookings...");
                        try {
                            List<Meeting> myMeetings = memberService.viewMyMeetings(username);
                        } catch (NoMeetingsFoundException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Exiting.");
                        break;
                }
                break;

            default:
                System.out.println("Unknown user type. Exiting.");
                break;
        }

        scanner.close();
    }
}
