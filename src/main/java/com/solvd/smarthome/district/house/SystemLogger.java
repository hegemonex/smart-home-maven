package com.solvd.smarthome.district.house;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SystemLogger {
    private static final Logger logger = LogManager.getLogger(SystemLogger.class);

    public static void log(String message) {
        logger.info("[SMART HOME LOG]: {}", message);
    }

}