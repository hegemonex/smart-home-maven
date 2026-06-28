package com.solvd.smarthome.district.house;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmartHomeLogger implements AutoCloseable {
    private static final Logger logger = LogManager.getLogger(SmartHomeLogger.class);

    public SmartHomeLogger() {
        logger.info("SmartHome logger created");
    }

    public void log(String message) {
        logger.info("LOG: {}", message);
    }

    @Override
    public void close() throws Exception {
        logger.info("CLOSED");
    }
}
