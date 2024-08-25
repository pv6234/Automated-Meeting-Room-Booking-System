package com.demo.beans;

public class Room {
    static int roomCount = 0;

    private int id;
    private String name;
    boolean seatingCapacityLessThanFive;
    boolean seatingCapacityBetweenFiveAndTen;
    boolean seatingCapacityGreaterThanTen;
    boolean projector;
    boolean wifiConnection;
    boolean conferenceCallFacility;
    boolean whiteboard;
    boolean waterDispenser;
    boolean TV;
    boolean coffeeMachine;


    public Room(int id, String name, boolean seatingCapacityLessThanFive, boolean seatingCapacityBetweenFiveAndTen,
                boolean seatingCapacityGreaterThanTen, boolean projector, boolean wifiConnection, boolean conferenceCallFacility,
                boolean whiteboard, boolean waterDispenser, boolean TV, boolean coffeeMachine) {
        this.id = generateRoomId();
        this.name = name;
        this.seatingCapacityLessThanFive = seatingCapacityLessThanFive;
        this.seatingCapacityBetweenFiveAndTen = seatingCapacityBetweenFiveAndTen;
        this.seatingCapacityGreaterThanTen = seatingCapacityGreaterThanTen;
        this.projector = projector;
        this.wifiConnection = wifiConnection;
        this.conferenceCallFacility = conferenceCallFacility;
        this.whiteboard = whiteboard;
        this.waterDispenser = waterDispenser;
        this.TV = TV;
        this.coffeeMachine = coffeeMachine;
    }

    public int generateRoomId(){
        roomCount++;
        return 1000*roomCount;
    }

    public boolean isCoffeeMachine() {
        return coffeeMachine;
    }

    public void setCoffeeMachine(boolean coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public boolean isTV() {
        return TV;
    }

    public void setTV(boolean TV) {
        this.TV = TV;
    }

    public boolean isWaterDispenser() {
        return waterDispenser;
    }

    public void setWaterDispenser(boolean waterDispenser) {
        this.waterDispenser = waterDispenser;
    }

    public boolean isWhiteboard() {
        return whiteboard;
    }

    public void setWhiteboard(boolean whiteboard) {
        this.whiteboard = whiteboard;
    }

    public boolean isConferenceCallFacility() {
        return conferenceCallFacility;
    }

    public void setConferenceCallFacility(boolean conferenceCallFacility) {
        this.conferenceCallFacility = conferenceCallFacility;
    }

    public boolean isWifiConnection() {
        return wifiConnection;
    }

    public void setWifiConnection(boolean wifiConnection) {
        this.wifiConnection = wifiConnection;
    }

    public boolean isProjector() {
        return projector;
    }

    public void setProjector(boolean projector) {
        this.projector = projector;
    }

    public boolean isSeatingCapacityGreaterThanTen() {
        return seatingCapacityGreaterThanTen;
    }

    public void setSeatingCapacityGreaterThanTen(boolean seatingCapacityGreaterThanTen) {
        this.seatingCapacityGreaterThanTen = seatingCapacityGreaterThanTen;
    }

    public boolean isSeatingCapacityBetweenFiveAndTen() {
        return seatingCapacityBetweenFiveAndTen;
    }

    public void setSeatingCapacityBetweenFiveAndTen(boolean seatingCapacityBetweenFiveAndTen) {
        this.seatingCapacityBetweenFiveAndTen = seatingCapacityBetweenFiveAndTen;
    }

    public boolean isSeatingCapacityLessThanFive() {
        return seatingCapacityLessThanFive;
    }

    public void setSeatingCapacityLessThanFive(boolean seatingCapacityLessThanFive) {
        this.seatingCapacityLessThanFive = seatingCapacityLessThanFive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seatingCapacityLessThanFive=" + seatingCapacityLessThanFive +
                ", seatingCapacityBetweenFiveAndTen=" + seatingCapacityBetweenFiveAndTen +
                ", seatingCapacityGreaterThanTen=" + seatingCapacityGreaterThanTen +
                ", projector=" + projector +
                ", wifiConnection=" + wifiConnection +
                ", conferenceCallFacility=" + conferenceCallFacility +
                ", whiteboard=" + whiteboard +
                ", waterDispenser=" + waterDispenser +
                ", TV=" + TV +
                ", coffeeMachine=" + coffeeMachine +
                '}';
    }
}

