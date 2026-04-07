package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Connectable;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SmartPlug extends SmartDevice implements Connectable {

    private String connectedDevice;
    private BigDecimal maxWattage;
    private boolean active;

    public SmartPlug(String name, BigDecimal price, LocalDate installedDate, String connectedDevice, BigDecimal maxWattage, boolean conected) {
        super(name, price, installedDate, conected);
        this.connectedDevice = connectedDevice;
        this.maxWattage = maxWattage;
        this.active = false;
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
        System.out.println(name + " is plugging");
    }

    @Override
    public void connect() {
        System.out.println(name + " is connecting");
    }

    @Override
    public void disconnect() {
        System.out.println(name + " is disconnecting");
    }
}