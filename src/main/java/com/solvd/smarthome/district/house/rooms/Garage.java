package com.solvd.smarthome.district.house.rooms;

import com.solvd.smarthome.district.house.devices.DeviceGroup;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.math.BigDecimal;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Garage extends Room {

    private int carCapacity;

    public Garage(String name, BigDecimal area, List<DeviceGroup> groups, int carCapacity) {
        super(name, area, groups);
        this.carCapacity = carCapacity;
    }

    public Garage() {
    }

    public int getCarCapacity() {
        return carCapacity;
    }

    public void setCarCapacity(int carCapacity) {
        this.carCapacity = carCapacity;
    }

    @Override
    public String listGroups() {
        return super.listGroups() + "      Garage capacity: " + carCapacity + " cars\n";
    }
}