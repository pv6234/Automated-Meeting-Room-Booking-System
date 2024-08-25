package com.demo.dao;

import com.demo.beans.Meeting;
import com.demo.beans.Room;
import com.demo.exceptions.RoomNotAvailableException;
import com.demo.exceptions.RoomNotFoundException;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDaoImpl implements RoomDao {

    private Map<String, Integer> amenitiesMap;
    List<String> classroomTraining = new ArrayList<String>();
    List<String> onlineTraining = new ArrayList<String>();
    List<String> conferenceCall = new ArrayList<String>();
    List<String> business = new ArrayList<String>();

    private Connection conn;

    public RoomDaoImpl() {

        conn= DBUtil.getMyConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        amenitiesMap = new HashMap<>();

        amenitiesMap.put("seatingCapacityLessThanFive", 0);   // Seating capacity <=5
        amenitiesMap.put("seatingCapacityBetweenFiveAndTen", 10);  // Seating capacity >5<=10
        amenitiesMap.put("seatingCapacityGreaterThanTen", 20);  // Seating capacity > 10
        amenitiesMap.put("projector", 5);   // Projector
        amenitiesMap.put("wifiConnection", 10);  // WiFi Connection
        amenitiesMap.put("conferenceCallFacility", 15);  // Conference call facility
        amenitiesMap.put("whiteboard", 5);   // Whiteboard
        amenitiesMap.put("waterDispenser", 5);   // Water dispenser
        amenitiesMap.put("TV", 10);  // TV
        amenitiesMap.put("coffeeMachine", 10); // Coffee machine

        //Minimum requirements for respective meeting types
        classroomTraining.add("whiteboard");
        classroomTraining.add("projector");
        onlineTraining.add("wifi");
        onlineTraining.add("projector");
        conferenceCall.add("conferenceCallFacility");
        business.add("projector");
    }

    @Override
    public void save(Room newRoom) {
        String sql = "insert into rooms (id, name, seatingCapacityLessThanFive, seatingCapacityBetweenFiveAndTen, " +
                "seatingCapacityGreaterThanTen, projector, wifiConnection, conferenceCallFacility, " +
                "whiteboard, waterDispenser, TV, coffeeMachine) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newRoom.getId());
            pstmt.setString(2, newRoom.getName());
            pstmt.setBoolean(3, newRoom.isSeatingCapacityLessThanFive());
            pstmt.setBoolean(4, newRoom.isSeatingCapacityBetweenFiveAndTen());
            pstmt.setBoolean(5, newRoom.isSeatingCapacityGreaterThanTen());
            pstmt.setBoolean(6, newRoom.isProjector());
            pstmt.setBoolean(7, newRoom.isWifiConnection());
            pstmt.setBoolean(8, newRoom.isConferenceCallFacility());
            pstmt.setBoolean(9, newRoom.isWhiteboard());
            pstmt.setBoolean(10, newRoom.isWaterDispenser());
            pstmt.setBoolean(11, newRoom.isTV());
            pstmt.setBoolean(12, newRoom.isCoffeeMachine());

            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRoom(String name) {
        String sql = "delete from rooms where name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);

            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void changeRoomName(String name, String newName) {
        String sql = "update rooms set name = ? where name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, name);

            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void changeAmenitiesForARoom(String name,
                                        boolean seatingCapacityLessThanFive,
                                        boolean seatingCapacityBetweenFiveAndTen,
                                        boolean seatingCapacityGreaterThanTen,
                                        boolean projector,
                                        boolean wifiConnection,
                                        boolean conferenceCallFacility,
                                        boolean whiteboard,
                                        boolean waterDispenser,
                                        boolean tv,
                                        boolean coffeeMachine) {

        String sql = "update rooms set seatingCapacityLessThanFive = ?, seatingCapacityBetweenFiveAndTen = ?, " +
                "seatingCapacityGreaterThanTen = ?, projector = ?, wifiConnection = ?, " +
                "conferenceCallFacility = ?, whiteboard = ?, waterDispenser = ?, TV = ?, coffeeMachine = ? " +
                "where name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, seatingCapacityLessThanFive);
            pstmt.setBoolean(2, seatingCapacityBetweenFiveAndTen);
            pstmt.setBoolean(3, seatingCapacityGreaterThanTen);
            pstmt.setBoolean(4, projector);
            pstmt.setBoolean(5, wifiConnection);
            pstmt.setBoolean(6, conferenceCallFacility);
            pstmt.setBoolean(7, whiteboard);
            pstmt.setBoolean(8, waterDispenser);
            pstmt.setBoolean(9, tv);
            pstmt.setBoolean(10, coffeeMachine);
            pstmt.setString(11, name);

            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<Room> getRooms(String meetType) throws RoomNotFoundException {
        List<Room> rooms = new ArrayList<>();
        List<String> amenities;

        if (meetType.equalsIgnoreCase("classroomTraining")) {
            amenities = classroomTraining;
        } else if (meetType.equalsIgnoreCase("onlineTraining")) {
            amenities = onlineTraining;
        } else if (meetType.equalsIgnoreCase("conferenceCall")) {
            amenities = conferenceCall;
        } else if (meetType.equalsIgnoreCase("business")) {
            amenities = business;
        } else {
            return rooms;
        }

        StringBuilder sql = new StringBuilder("select * from rooms where ");
        for (int i = 0; i < amenities.size(); i++) {
            sql.append(amenities.get(i)).append(" = true");
            if (i < amenities.size() - 1) {
                sql.append(" and ");
            }
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql.toString());
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Room room = new Room(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getBoolean("seatingCapacityLessThanFive"),
                        rs.getBoolean("seatingCapacityBetweenFiveAndTen"),
                        rs.getBoolean("seatingCapacityGreaterThanTen"),
                        rs.getBoolean("projector"),
                        rs.getBoolean("wifiConnection"),
                        rs.getBoolean("conferenceCallFacility"),
                        rs.getBoolean("whiteboard"),
                        rs.getBoolean("waterDispenser"),
                        rs.getBoolean("TV"),
                        rs.getBoolean("coffeeMachine")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            throw new RoomNotFoundException("No eligible rooms are available currently!");
        }

        return rooms;
    }

    @Override
    public int getHourlyRoomCredits(String roomName) {
        int totalCredits = 0;

        String sql = "select seatingCapacityLessThanFive, seatingCapacityBetweenFiveAndTen, seatingCapacityGreaterThanTen, " +
                "projector, wifiConnection, conferenceCallFacility, whiteboard, waterDispenser, TV, coffeeMachine " +
                "from rooms where name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomName);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Check each amenity and add its corresponding credit value from amenitiesMap
                    if (rs.getBoolean("seatingCapacityLessThanFive")) {
                        totalCredits += amenitiesMap.get("seatingCapacityLessThanFive");
                    }
                    if (rs.getBoolean("seatingCapacityBetweenFiveAndTen")) {
                        totalCredits += amenitiesMap.get("seatingCapacityBetweenFiveAndTen");
                    }
                    if (rs.getBoolean("seatingCapacityGreaterThanTen")) {
                        totalCredits += amenitiesMap.get("seatingCapacityGreaterThanTen");
                    }
                    if (rs.getBoolean("projector")) {
                        totalCredits += amenitiesMap.get("projector");
                    }
                    if (rs.getBoolean("wifiConnection")) {
                        totalCredits += amenitiesMap.get("wifiConnection");
                    }
                    if (rs.getBoolean("conferenceCallFacility")) {
                        totalCredits += amenitiesMap.get("conferenceCallFacility");
                    }
                    if (rs.getBoolean("whiteboard")) {
                        totalCredits += amenitiesMap.get("whiteboard");
                    }
                    if (rs.getBoolean("waterDispenser")) {
                        totalCredits += amenitiesMap.get("waterDispenser");
                    }
                    if (rs.getBoolean("TV")) {
                        totalCredits += amenitiesMap.get("TV");
                    }
                    if (rs.getBoolean("coffeeMachine")) {
                        totalCredits += amenitiesMap.get("coffeeMachine");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalCredits;
    }

    @Override
    public void saveSchedule(Meeting newMeeting) throws RoomNotAvailableException {
        String sql = "insert into meeting (meetingId, meetingTitle, date, durationInHours, creator, roomId, meetingType) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, newMeeting.getMeetingId());
            pstmt.setString(2, newMeeting.getMeetingTitle());
            pstmt.setDate(3, Date.valueOf(newMeeting.getDate()));
            pstmt.setInt(4, newMeeting.getDurationInHours());
            pstmt.setString(5, newMeeting.getOrganizer());
            pstmt.setInt(6, newMeeting.getRoomId());
            pstmt.setString(7, newMeeting.getMeetingType());
            pstmt.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
                throw new RoomNotAvailableException("This room has been already booked for the given date.");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
