package com.solvd.smarthome.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public enum DeviceStatus {

    ONLINE(1, "Device is online and fully operational", true),
    OFFLINE(2, "Device is offline and not reachable", false),
    STANDBY(3, "Device is in low-power standby mode", true),
    ERROR(4, "Device has encountered a critical error", false),
    MAINTENANCE(5, "Device is undergoing scheduled maintenance", false),
    UPDATING(6, "Device is downloading and applying a firmware update", true);

    private static final Logger logger = LogManager.getLogger(DeviceStatus.class);

    private final int statusCode;
    private final String description;
    private final boolean isOperational;

    static {
        logger.info("DeviceStatus enum loaded.");
    }

    DeviceStatus(int statusCode, String description, boolean isOperational) {
        this.statusCode = statusCode;
        this.description = description;
        this.isOperational = isOperational;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOperational() {
        return isOperational;
    }

    public static DeviceStatus fromCode(int code) {
        DeviceStatus result = Arrays.stream(values())
                .filter(s -> s.statusCode == code)
                .findFirst()
                .orElse(null);

        if (result == null) {
            logger.warn("No DeviceStatus found for code: {}", code);
        }

        return result;
    }

    @Override
    public String toString() {
        return name() +
                " (code=" + statusCode +
                ", operational=" + isOperational + ")";
    }
}