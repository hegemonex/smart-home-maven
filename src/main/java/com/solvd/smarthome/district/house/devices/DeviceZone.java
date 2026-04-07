package com.solvd.smarthome.district.house.devices;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DeviceZone {

    private String zoneName;
    private String location;
    private final Map<Device, String> devicePlacementMap;

    public DeviceZone(String zoneName, String location) {
        this.zoneName = zoneName;
        this.location = location;
        this.devicePlacementMap = new HashMap<>();
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<Device, String> getDevicePlacementMap() {
        return devicePlacementMap;
    }

    public void addDevice(Device device, String placement) {
        devicePlacementMap.put(device, placement);
    }

    public void removeDevice(Device device) {
        devicePlacementMap.remove(device);
    }

    public String listDevices() {
        if (devicePlacementMap.isEmpty()) {
            return "Zone [" + zoneName + " - " + location + "]: no devices";
        }

        String devices = devicePlacementMap.entrySet().stream()
                .map(e -> e.getKey().getName() + " [" + e.getValue() + "]")
                .collect(Collectors.joining(", "));

        return "Zone [" + zoneName + " - " + location + "]: " + devices;
    }
}