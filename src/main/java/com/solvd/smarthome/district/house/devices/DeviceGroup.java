package com.solvd.smarthome.district.house.devices;

import com.solvd.smarthome.exceptions.DeviceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceGroup {

    private String groupName;
    private String category;
    private final List<DeviceZone> zones;

    public DeviceGroup(String groupName, String category, List<DeviceZone> zones) {
        this.groupName = groupName;
        this.category = category;
        this.zones = zones != null ? new ArrayList<>(zones) : new ArrayList<>();
    }

    public String getGroupName() {
        if (groupName == null) {
            throw new DeviceNotFoundException("The devices weren't found");
        }
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<DeviceZone> getZones() {
        return zones;
    }

    public void addZone(DeviceZone zone) {
        zones.add(zone);
    }

    public boolean removeZone(DeviceZone z) {
        return zones.remove(z);
    }

    public boolean isEmpty() {
        return zones.isEmpty();
    }

    public int size() {
        return zones.size();
    }

    public DeviceZone get(int i) {
        return zones.get(i);
    }

    public DeviceZone getFirstZone() {
        return zones.isEmpty() ? null : zones.getFirst();
    }

    public String listZones() {
        if (zones.isEmpty()) {
            return "      [" + category + "] " + groupName + ": no zones";
        }

        String zoneList = zones.stream()
                .map(DeviceZone::listDevices)
                .collect(Collectors.joining("\n"));

        return "      [" + category + "] " + groupName + ":\n" + zoneList + "\n";
    }
}