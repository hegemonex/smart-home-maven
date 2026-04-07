package com.solvd.smarthome.district.house.devices.sensors;

import com.solvd.smarthome.district.house.devices.Monitorable;
import com.solvd.smarthome.district.house.devices.Security;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SecurityCamera extends Sensor implements Monitorable, Security {

    private String resolution;
    private String sensorType;
    private boolean recording;
    private LocalDateTime lastMotionDetected;

    public SecurityCamera(String name, BigDecimal price, LocalDate installedDate, String resolution, String sensorType, Double sensorValue) {
        super(name, price, installedDate, sensorValue, sensorType);
        this.resolution = resolution;
        this.sensorType = sensorType;
        this.recording = false;
    }

    public SecurityCamera() {
        super();
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getSensorType() {
        return sensorType;
    }

    public boolean isRecording() {
        return recording;
    }

    public void setRecording(boolean recording) {
        this.recording = recording;
    }

    public LocalDateTime getLastMotionDetected() {
        return lastMotionDetected;
    }

    public void setLastMotionDetected(LocalDateTime lastMotionDetected) {
        this.lastMotionDetected = lastMotionDetected;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | Resolution: " + resolution + ", Sensor: " + sensorType;
    }

    public String recordingStatus() {
        if (recording) {
            return getName() + " is currently recording.";
        } else {
            return getName() + " is not recording.";
        }
    }

    public String logMotion() {
        lastMotionDetected = LocalDateTime.now();
        return getName() + " detected motion at " + lastMotionDetected;
    }

    @Override
    public void operate() {
        System.out.println(name + " is monitoring");
    }

    @Override
    public void monitor() {
        System.out.println(name + " is monitoring");
    }

    @Override
    public void detect() {
        System.out.println(name + " is detecting");
    }

    @Override
    public void alert() {
        System.out.println(name + " is detecting");
    }
}