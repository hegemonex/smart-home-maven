package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Connectable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class SmartPlug extends SmartDevice implements Connectable {

    private String connectedDevice;
    private BigDecimal maxWattage;
    private boolean active;
    private static Logger logger = LogManager.getLogger(SmartPlug.class);

    public SmartPlug(String name, BigDecimal price, LocalDate installedDate, String connectedDevice, BigDecimal maxWattage, boolean conected) {
        super(name, price, installedDate, conected);
        this.connectedDevice = connectedDevice;
        this.maxWattage = maxWattage;
        this.active = false;
    }

    public SmartPlug() {
    }

    public String getConnectedDevice() {
        return connectedDevice;
    }

    public void setConnectedDevice(String connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    public BigDecimal getMaxWattage() {
        return maxWattage;
    }

    public void setMaxWattage(BigDecimal maxWattage) {
        this.maxWattage = maxWattage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | Connected: " + connectedDevice + ", Max Wattage: " + maxWattage + "W";
    }

    public String turnOn() {
        active = true;
        return connectedDevice + " is now ON.";
    }

    public String turnOff() {
        active = false;
        return connectedDevice + " is now OFF.";
    }

    public String showPowerUsage() {
        return connectedDevice + " is drawing up to " + maxWattage + "W.";
    }

    @Override
    public void operate() {
        logger.info("Operating SmartPlug");
    }

    @Override
    public void connect() {
        logger.info("Connecting SmartPlug");
    }

    @Override
    public void disconnect() {
        logger.info("Disconnecting SmartPlug");
    }
}