package com.solvd.smarthome.district.house.rooms;

import com.solvd.smarthome.district.house.devices.DeviceGroup;

import java.math.BigDecimal;
import java.util.List;

public class Garden extends Room {

    private boolean hasIrrigation;

    public Garden(String name, BigDecimal area, List<DeviceGroup> groups, boolean hasIrrigation) {
        super(name, area, groups);
        this.hasIrrigation = hasIrrigation;
    }

    public boolean isHasIrrigation() {
        return hasIrrigation;
    }

    public void setHasIrrigation(boolean hasIrrigation) {
        this.hasIrrigation = hasIrrigation;
    }
}