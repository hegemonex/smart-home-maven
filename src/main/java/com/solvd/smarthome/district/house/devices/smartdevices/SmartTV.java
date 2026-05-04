package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Connectable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class SmartTV extends SmartDevice implements Connectable {

    private String brand;
    private int screenSize;
    private boolean isOn;
    private static Logger logger = LogManager.getLogger(SmartTV.class);

    public SmartTV(String name, BigDecimal price, LocalDate installedDate, String brand, int screenSize, boolean connected) {
        super(name, price, installedDate, connected);
        this.brand = brand;
        this.screenSize = screenSize;
        this.isOn = false;
    }

    public SmartTV() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | Brand: " + brand + ", Screen: " + screenSize + " inches";
    }

    public String turnOn() {
        isOn = true;
        return brand + " TV is now ON.";
    }

    public String turnOff() {
        isOn = false;
        return brand + " TV is now OFF.";
    }

    @Override
    public void operate() {
        logger.info("Operating SmartTV");
    }

    @Override
    public void connect() {
        logger.info("Connecting SmartTV");
    }

    @Override
    public void disconnect() {
        logger.info("Disconnecting SmartTV");
    }
}
