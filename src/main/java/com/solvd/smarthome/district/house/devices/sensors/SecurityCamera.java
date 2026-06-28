package com.solvd.smarthome.district.house.devices.sensors;

import com.solvd.smarthome.district.house.devices.Monitorable;
import com.solvd.smarthome.district.house.devices.Security;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SecurityCamera extends Sensor implements Monitorable, Security {

    private String resolution;
    private String sensorType;
    private boolean recording;
    private LocalDateTime lastMotionDetected;
    private static final Logger logger =  LogManager.getLogger(SecurityCamera.class);


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
        logger.info("{} is monitoring", name);
    }

    @Override
    public void monitor() {
        logger.info("{} is monitoring", name);
    }

    @Override
    public void detect() {
        logger.info("{} is monitoring", name);
    }

    @Override
    public void alert() {
        logger.info("{} is monitoring", name);
    }
}