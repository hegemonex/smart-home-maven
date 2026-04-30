package com.solvd.smarthome.district.house.devices.climatedevice;

import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.exceptions.InvalidTemperatureException;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class ClimateDevice extends Device {

    protected double temperatureSetting;

    public ClimateDevice(String name, BigDecimal price, LocalDate installedDate, double temperatureSetting) {
        super(name, price, installedDate);
        this.temperatureSetting = temperatureSetting;
    }

    public ClimateDevice() {}

    public double getTemperatureSetting() {
        return temperatureSetting;
    }

    public void setTemperatureSetting(double temperatureSetting) {
        if (temperatureSetting < 0 || temperatureSetting > 100) {
            throw new InvalidTemperatureException("Invalid temperature setting");
        }
        this.temperatureSetting = temperatureSetting;
    }

    @Override
    public void operate() {
        System.out.println(name + " is controlling temperature at " + temperatureSetting + "°C");
    }
}