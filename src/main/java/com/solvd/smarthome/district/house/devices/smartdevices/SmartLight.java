package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Connectable;
import com.solvd.smarthome.district.house.devices.Switchable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class SmartLight extends SmartDevice implements Connectable, Switchable {

    private String color;
    private String model;
    private int brightness;
    private static Logger logger = LogManager.getLogger(SmartLight.class);


    public SmartLight(String name, BigDecimal price, LocalDate installedDate, String color, String model, int brightness, boolean connected) {
        super(name, price, installedDate, connected);
        this.color = color;
        this.model = model;
        this.brightness = brightness;
    }

    public SmartLight() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | Model: " + model + ", Color: " + color + ", Brightness: " + brightness + "%";
    }

    public String turnOn() {
        return getName() + " light is ON at " + brightness + "% brightness.";
    }

    public String turnOff() {
        return getName() + " light is OFF.";
    }

    @Override
    public void operate() {
        logger.info("Operating through the smart network.");
    }

    @Override
    public void connect() {
        logger.info("Connecting through the smart network.");
    }

    @Override
    public void disconnect() {
        logger.info("Disconnecting through the smart network.");
    }

    @Override
    public void switchOn() {
        logger.info("Switching through the smart network.");
    }

    @Override
    public void switchOff() {
        logger.info("Switching through the smart network.");
    }
}