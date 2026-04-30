package com.solvd.smarthome.enums;

import jakarta.xml.bind.annotation.XmlEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

@XmlEnum
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

    private static  Logger logger = LogManager.getLogger(RoomType.class);

    private  int floorLevel;
    private  int deviceAmount;
    private  boolean needsVentilation;

    static {
        logger.info("RoomType enum loaded.");
    }

    RoomType(int deviceAmount, boolean needsVentilation, int floorLevel) {
        this.floorLevel = floorLevel;
        this.deviceAmount = deviceAmount;
        this.needsVentilation = needsVentilation;
    }

    public static RoomType[] forFloor(int level) {
        RoomType[] result = Arrays.stream(values())
                .filter(rt -> rt.floorLevel == level)
                .toArray(RoomType[]::new);

        logger.debug("Found {} room types for floor {}", result.length, level);
        return result;
    }

    public String capacityWarning(int installedCount) {
        if (installedCount > deviceAmount) {
            logger.warn("{} exceeds recommended device capacity: {} > {}", name(), installedCount, deviceAmount);
            return "WARNING: " + name() + " has " + installedCount
                    + " devices but the recommended max is " + deviceAmount + ".";
        }

        logger.debug("{} device count within limits: {}", name(), installedCount);
        return name() + " device count (" + installedCount + ") is within safe limits.";
    }

    public int getDeviceAmount() {
        return deviceAmount;
    }

    public boolean isVentilationNeeded() {
        return needsVentilation;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public abstract String describeFunction();
}