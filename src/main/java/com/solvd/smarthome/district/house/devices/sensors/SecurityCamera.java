package com.solvd.smarthome.district.house.devices.sensors;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solvd.smarthome.district.house.devices.Monitorable;
import com.solvd.smarthome.district.house.devices.Security;
import com.solvd.smarthome.timeadapter.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityCamera extends Sensor implements Monitorable, Security {

    private String resolution;
    private String sensorType;
    private boolean recording;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime lastMotionDetected;

    private static Logger logger = LogManager.getLogger(SecurityCamera.class);


    public SecurityCamera(String name, BigDecimal price, LocalDate installedDate, String resolution, String sensorType, Double sensorValue) {
        super(name, price, installedDate, sensorValue, sensorType);
        this.resolution = resolution;
        this.sensorType = sensorType;
        this.recording = false;
    }

    public SecurityCamera() {
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