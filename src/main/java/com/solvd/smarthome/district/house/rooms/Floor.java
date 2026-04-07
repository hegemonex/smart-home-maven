package com.solvd.smarthome.district.house.rooms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Floor {

    private int floorNumber;
    private String label;
    private final List<Room> rooms;

    public Floor(int floorNumber, String label, List<Room> rooms) {
        this.floorNumber = floorNumber;
        this.label = label;
        this.rooms = rooms != null ? new ArrayList<>(rooms) : new ArrayList<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public boolean removeRoom(Room room) {
        return rooms.remove(room);
    }

    public boolean isEmpty() {
        return rooms.isEmpty();
    }

    public int size() {
        return rooms.size();
    }

    public Room getFirstRoom() {
        return rooms.isEmpty() ? null : rooms.get(0);
    }

    public String listRooms() {
        if (rooms.isEmpty()) {
            return "  Floor " + floorNumber + " (" + label + "): no rooms";
        }

        String roomList = rooms.stream()
                .map(Room::listGroups)
                .collect(Collectors.joining());

        return "  Floor " + floorNumber + " — " + label + ":\n" + roomList;
    }
}