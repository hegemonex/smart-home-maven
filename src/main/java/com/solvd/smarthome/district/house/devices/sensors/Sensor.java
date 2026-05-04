package com.solvd.smarthome.district.house.devices.sensors;

import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.exceptions.SecurityAlertException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Sensor extends Device {

    protected double sensorValue;
    protected String sensorType;
    private static final Logger logger = LogManager.getLogger(Sensor.class);

    public Sensor(String name, BigDecimal price, LocalDate installedDate, double sensorValue, String sensorType) {
        super(name, price, installedDate);
        this.sensorValue = sensorValue;
        this.sensorType = sensorType;
    }

    public Sensor() {
    }

    public void setSensorType(String sensorType) {
        if (sensorType == null) {
            throw new SecurityAlertException("Sensor type cannot be null");
        }
        this.sensorType = sensorType;
    }

    public double getSensorValue() {
        return sensorValue;
    }

    public void setSensorValue(double sensorValue) {
        this.sensorValue = sensorValue;
    }

    @Override
    public void operate() {
        logger.info("{} is detecting something. Current value: {}", name, sensorValue);
    }
}