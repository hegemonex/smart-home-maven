package com.solvd.smarthome.district.house.rooms;

import com.solvd.smarthome.district.house.devices.DeviceGroup;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlAccessorType(XmlAccessType.FIELD)
public class Room {

    protected String name;
    protected BigDecimal area;

    @XmlElementWrapper(name = "devicegroups")
    @XmlElement(name = "devicegroup")
    private  List<DeviceGroup> deviceGroups;

    public Room(String name, BigDecimal area, List<DeviceGroup> deviceGroups) {
        this.name = name;
        this.area = area;
        this.deviceGroups = deviceGroups != null ? new ArrayList<>(deviceGroups) : new ArrayList<>();
    }

    public Room() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public void addGroup(DeviceGroup group) {
        deviceGroups.add(group);
    }

    public boolean removeGroup(DeviceGroup group) {
        return deviceGroups.remove(group);
    }

    public boolean isEmpty() {
        return deviceGroups.isEmpty();
    }

    public int size() {
        return deviceGroups.size();
    }

    public DeviceGroup getFirstGroup() {
        return deviceGroups.isEmpty() ? null : deviceGroups.get(0);
    }

    public String listGroups() {
        if (deviceGroups.isEmpty()) {
            return "    Room: " + name + " — no device groups";
        }

        String groups = deviceGroups.stream()
                .map(DeviceGroup::listZones)
                .collect(Collectors.joining());

        return "    Room: " + name + " (" + area + " sqm)\n" + groups;
    }
}