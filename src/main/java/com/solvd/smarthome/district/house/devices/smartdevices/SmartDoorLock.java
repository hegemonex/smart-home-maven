package com.solvd.smarthome.district.house.devices.smartdevices;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.solvd.smarthome.district.house.devices.Connectable;
import com.solvd.smarthome.district.house.devices.Device;
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
public class SmartDoorLock extends Device implements Connectable {

    private String lockModel;
    private boolean locked;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime lastUnlocked;

    private static Logger logger = LogManager.getLogger(SmartDoorLock.class);

    public SmartDoorLock(String name, BigDecimal price, LocalDate installedDate, String lockModel) {
        super(name, price, installedDate);
        this.lockModel = lockModel;
        this.locked = true;
    }

    public SmartDoorLock() {
    }

    public String getLockModel() {
        return lockModel;
    }

    public void setLockModel(String lockModel) {
        this.lockModel = lockModel;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getLastUnlocked() {
        return lastUnlocked;
    }

    public void setLastUnlocked(LocalDateTime lastUnlocked) {
        this.lastUnlocked = lastUnlocked;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | Model: " + lockModel + ", State: " + (locked ? "Locked" : "Unlocked");
    }

    public String lock() {
        locked = true;
        return getName() + " is now LOCKED.";
    }

    public String unlock() {
        locked = false;
        lastUnlocked = LocalDateTime.now();
        return getName() + " is now UNLOCKED at " + lastUnlocked;
    }

    @Override
    public void operate() {
        logger.info("{} is locking");
    }

    @Override
    public void connect() {
        logger.info("{} is connecting");
    }

    @Override
    public void disconnect() {
        logger.info("{} is disconnecting");
    }
}
