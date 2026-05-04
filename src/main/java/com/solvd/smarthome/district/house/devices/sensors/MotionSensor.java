package com.solvd.smarthome.district.house.devices.sensors;

import com.solvd.smarthome.district.house.devices.Monitorable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class MotionSensor extends Sensor implements Monitorable {

    private int sensitivity;
    private boolean motionDetected;
    private int detectionRangeMeters;
    private static Logger logger = LogManager.getLogger(MotionSensor.class);

    public MotionSensor(String name, BigDecimal price, LocalDate installedDate, int sensitivity, int detectionRangeMeters, Double sensorValue, String sensorType) {
        super(name, price, installedDate, sensorValue, sensorType);
        this.sensitivity = sensitivity;
        this.detectionRangeMeters = detectionRangeMeters;
        this.motionDetected = false;
    }

    public MotionSensor() {
    }

    public int getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }

    public boolean isMotionDetected() {
        return motionDetected;
    }

    public void setMotionDetected(boolean motionDetected) {
        this.motionDetected = motionDetected;
    }

    public int getDetectionRangeMeters() {
        return detectionRangeMeters;
    }

    public void setDetectionRangeMeters(int detectionRangeMeters) {
        this.detectionRangeMeters = detectionRangeMeters;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | Sensitivity: " + sensitivity + ", Range: " + detectionRangeMeters + "m";
    }

    @Override
    public void operate() {
        System.out.println(name + " is sensoring");
    }

    public String detectMotion() {
        if (motionDetected) {
            return getName() + ": Motion detected! Alert!";
        } else {
            return getName() + ": No motion detected.";
        }
    }

    @Override
    public void monitor() {
        logger.info("{} Monitoring SmartTV", name);
    }

    @Override
    public void detect() {
        logger.info("{} Detecting SmartTV", name);
    }
}