package com.solvd.smarthome.district.house.devices.climatedevice;

import com.solvd.smarthome.district.house.devices.TemperatureControl;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Thermostat extends ClimateDevice implements TemperatureControl {

    private double currentTemp;
    private double targetTemp;

    public Thermostat(String name, BigDecimal price, LocalDate installedDate, double currentTemp, double targetTemp, Double maximumTemp) {
        super(name, price, installedDate, maximumTemp);
        this.currentTemp = currentTemp;
        this.targetTemp = targetTemp;
    }

    public Thermostat() {
    }

    public double getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }

    public double getTargetTemp() {
        return targetTemp;
    }

    public void setTargetTemp(double targetTemp) {
        this.targetTemp = targetTemp;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | Current: " + currentTemp + "°C, Target: " + targetTemp + "°C";
    }

    public String adjustTemp() {
        return getName() + ": adjusting from " + currentTemp + "°C to target " + targetTemp + "°C.";
    }

    @Override
    public void operate() {
        System.out.println(name + " is thermostating");
    }

    @Override
    public void setTemperature(double temperature) {
        System.out.println("Temperature set to " + temperature);
    }
}
