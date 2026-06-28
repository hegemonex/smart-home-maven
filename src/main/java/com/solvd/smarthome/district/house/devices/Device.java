package com.solvd.smarthome.district.house.devices;

import com.solvd.smarthome.district.house.SmartHome;
import com.solvd.smarthome.exceptions.DeviceInstallationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Device {

    protected static int deviceCount = 0;
    private static final Logger logger = LogManager.getLogger(Device.class);

    static {
        logger.info("Device class loaded. Smart Home system starting...");
    }

    protected String name;
    protected BigDecimal price;
    protected LocalDate installedDate;

    {
        logger.info("A device object is being created.");
    }

    public Device(String name, BigDecimal price, LocalDate installedDate) {
        this.name = name;
        this.price = price;
        this.installedDate = installedDate;
        deviceCount++;
    }

    public Device() {
    }

    public static int getDeviceCount() {
        return deviceCount;
    }

    public final void printDeviceName() {
        logger.info(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getInstalledDate() {
        return installedDate;
    }

    public void setInstalledDate(LocalDate installedDate) throws DeviceInstallationException {
        if (installedDate == null) {
            throw new DeviceInstallationException("The device wasnt installed");
        }
        this.installedDate = installedDate;
    }

    public String deviceInfo() {
        return name + " (installed: " + installedDate + ", price: $" + price + ")";
    }

    // ABSTRACT METHOD (must be overridden)
    public abstract void operate();

    // OBJECT METHOD OVERRIDES

    @Override
    public String toString() {
        return "Device{name='" + name + "', price=" + price + ", installedDate=" + installedDate + "}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, installedDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Device other)) return false;
        return Objects.equals(name, other.name) && Objects.equals(price, other.price) && Objects.equals(installedDate, other.installedDate);
    }
}