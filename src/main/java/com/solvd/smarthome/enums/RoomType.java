package com.solvd.smarthome.enums;

import java.util.Arrays;

public enum RoomType {

    LIVING_ROOM(20, false, 1) {
        @Override
        public String describeFunction() {
            return "Central social space for relaxation and entertainment. "
                    + "Typically contains lighting, AV, and climate devices.";
        }
    },

    KITCHEN(15, true, 1) {
        @Override
        public String describeFunction() {
            return "Food preparation zone. Requires ventilation and appliance monitoring. "
                    + "High energy-use devices expected (oven, fridge, coffee machine).";
        }
    },

    BEDROOM(10, false, 2) {
        @Override
        public String describeFunction() {
            return "Private sleeping space. Prioritises quiet operation and "
                    + "dim lighting schedules. Motion sensors often suppressed at night.";
        }
    },

    BATHROOM(8, true, 1) {
        @Override
        public String describeFunction() {
            return "Wet room — all devices must be waterproof rated. "
                    + "Humidity sensors and heated floors are common.";
        }
    },

    OFFICE(12, false, 2) {
        @Override
        public String describeFunction() {
            return "Work and productivity space. Smart router and strong Wi-Fi mandatory. "
                    + "Adjustable lighting for video calls recommended.";
        }
    },

    GARAGE(6, true, 0) {
        @Override
        public String describeFunction() {
            return "Vehicle storage and workshop. Carbon-monoxide sensor required. "
                    + "Smart door lock and camera standard security kit.";
        }
    };

    static {
        System.out.println("[RoomType] Enum loaded — " + RoomType.values().length + " room types available.");
    }

    private final int floorLevel;
    private final int deviceAmount;
    private final boolean needsVentilation;

    RoomType(int deviceAmount, boolean needsVentilation, int floorLevel) {
        this.floorLevel = floorLevel;
        this.deviceAmount = deviceAmount;
        this.needsVentilation = needsVentilation;
    }

    public static RoomType[] forFloor(int level) {
        return Arrays.stream(values())
                .filter(rt -> rt.floorLevel == level)
                .toArray(RoomType[]::new);
    }

    public String capacityWarning(int installedCount) {
        if (installedCount > deviceAmount) {
            return "WARNING: " + name() + " has " + installedCount
                    + " devices but the recommended max is " + deviceAmount + ".";
        }
        return name() + " device count (" + installedCount + ") is within safe limits.";
    }

    public int getDeviceAmount() { return deviceAmount; }
    public boolean getVentilation() { return needsVentilation; }
    public int getFloorLevel() { return floorLevel; }

    public abstract String describeFunction();
}