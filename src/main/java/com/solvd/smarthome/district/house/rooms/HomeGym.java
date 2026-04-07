package com.solvd.smarthome.district.house.rooms;

import com.solvd.smarthome.district.house.devices.DeviceGroup;

import java.math.BigDecimal;
import java.util.List;

public class HomeGym extends Room {

    private int equipmentCount;

    public HomeGym(String name, BigDecimal area, List<DeviceGroup> groups, int equipmentCount) {
        super(name, area, groups);
        this.equipmentCount = equipmentCount;
    }

    public int getEquipmentCount() {
        return equipmentCount;
    }

    public void setEquipmentCount(int equipmentCount) {
        this.equipmentCount = equipmentCount;
    }
}