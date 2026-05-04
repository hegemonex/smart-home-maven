package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.exceptions.NetworkConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class SmartDevice extends Device {

    protected boolean connectedToWifi = false;
    private static final Logger logger = LogManager.getLogger(SmartDevice.class);

    public SmartDevice(String name, BigDecimal price, LocalDate installedDate, boolean connectedToWifi) {
        super(name, price, installedDate);
        this.connectedToWifi = connectedToWifi;
    }

    public SmartDevice() {
        super();
    }

    public boolean isConnectedToWifi() {
        if (!connectedToWifi) {
            throw new NetworkConnectionException("The device is not connected");
        }
        return connectedToWifi;
    }

    public void setConnectedToWifi(boolean connectedToWifi) {
        this.connectedToWifi = connectedToWifi;
    }

    @Override
    public void operate() {
        logger.info("{} is operating through the smart network.", name);
    }
}