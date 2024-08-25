package com.demo.service;

import com.demo.beans.Room;
import com.demo.dao.RoomDao;
import com.demo.dao.RoomDaoImpl;
import com.demo.dao.StorageFactory;

import java.util.Scanner;

public class AdminServiceImpl implements AdminService{
    private Room room;
    private RoomDao roomDao;

    public AdminServiceImpl() {
        roomDao = StorageFactory.getInstance();
    }

    public void addNewRoom() {
        Scanner sc=new Scanner(System.in);

        int roomId = room.generateRoomId();

        System.out.println("Enter room name: ");
        String roomName = sc.nextLine();

        System.out.println("Is the seating capacity less than 5? (true/false): ");
        boolean seatingCapacityLessThanFive = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Is the seating capacity between 5 and 10? (true/false): ");
        boolean seatingCapacityBetweenFiveAndTen = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Is the seating capacity greater than 10? (true/false): ");
        boolean seatingCapacityGreaterThanTen = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Does the room have a projector? (true/false): ");
        boolean projector = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Does the room have a WiFi connection? (true/false): ");
        boolean wifiConnection = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Does the room have a conference call facility? (true/false): ");
        boolean conferenceCallFacility = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Does the room have a whiteboard? (true/false): ");
        boolean whiteboard = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Does the room have a water dispenser? (true/false): ");
        boolean waterDispenser = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Does the room have a TV? (true/false): ");
        boolean TV = Boolean.parseBoolean(sc.nextLine());

        System.out.println("Does the room have a coffee machine? (true/false): ");
        boolean coffeeMachine = Boolean.parseBoolean(sc.nextLine());

        Room newRoom = new Room(roomId, roomName, seatingCapacityLessThanFive, seatingCapacityBetweenFiveAndTen,
                seatingCapacityGreaterThanTen, projector, wifiConnection, conferenceCallFacility,
                whiteboard, waterDispenser, TV, coffeeMachine);

        roomDao.save(newRoom);

    }

    public void editRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Configuration Menu");
        System.out.println("1. Delete Room");
        System.out.println("2. Change Room Name");
        System.out.println("3. Change Amenities in the room");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter room name: ");
        String name = scanner.nextLine();

        switch (choice) {
            case 1:
                roomDao.deleteRoom(name);
                break;
            case 2:
                System.out.println("Enter new room name:");
                String newName = scanner.nextLine();
                roomDao.changeRoomName(name, newName);
                break;
            case 3:
                Scanner sc = new Scanner(System.in);
                System.out.println("Select (T/F) for the Amenities you want ");
                System.out.println("Is the seating capacity less than 5? (true/false): ");
                boolean seatingCapacityLessThanFive = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Is the seating capacity between 5 and 10? (true/false): ");
                boolean seatingCapacityBetweenFiveAndTen = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Is the seating capacity greater than 10? (true/false): ");
                boolean seatingCapacityGreaterThanTen = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Does the room have a projector? (true/false): ");
                boolean projector = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Does the room have a WiFi connection? (true/false): ");
                boolean wifiConnection = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Does the room have a conference call facility? (true/false): ");
                boolean conferenceCallFacility = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Does the room have a whiteboard? (true/false): ");
                boolean whiteboard = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Does the room have a water dispenser? (true/false): ");
                boolean waterDispenser = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Does the room have a TV? (true/false): ");
                boolean TV = Boolean.parseBoolean(sc.nextLine());

                System.out.println("Does the room have a coffee machine? (true/false): ");
                boolean coffeeMachine = Boolean.parseBoolean(sc.nextLine());
                roomDao.changeAmenitiesForARoom(name,seatingCapacityLessThanFive, seatingCapacityBetweenFiveAndTen, seatingCapacityGreaterThanTen,projector,wifiConnection,conferenceCallFacility,whiteboard,waterDispenser,TV,coffeeMachine);


            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }
}
