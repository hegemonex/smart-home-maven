package com.solvd.smarthome.district.house.devices;

import com.solvd.smarthome.exceptions.DeviceNotFoundException;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceGroup {

    private String groupName;
    private String category;

    @XmlElementWrapper(name = "zones")
    @XmlElement(name = "devicezone")
    private List<DeviceZone> zones;

    public DeviceGroup(String groupName, String category, List<DeviceZone> zones) {
        this.groupName = groupName;
        this.category = category;
        this.zones = zones != null ? new ArrayList<>(zones) : new ArrayList<>();
    }

    public DeviceGroup() {
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