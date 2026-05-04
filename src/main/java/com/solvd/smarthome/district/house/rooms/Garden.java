package com.solvd.smarthome.district.house.rooms;

import com.solvd.smarthome.district.house.devices.DeviceGroup;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.math.BigDecimal;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Garden extends Room {

    private boolean hasIrrigation;

    public Garden(String name, BigDecimal area, List<DeviceGroup> groups, boolean hasIrrigation) {
        super(name, area, groups);
        this.hasIrrigation = hasIrrigation;
    }

    public Garden() {
    }

    public boolean isHasIrrigation() {
        return hasIrrigation;
    }

    public void setHasIrrigation(boolean hasIrrigation) {
        this.hasIrrigation = hasIrrigation;
    }
}