package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Connectable;
import com.solvd.smarthome.district.house.devices.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SmartDoorLock extends Device implements Connectable {

    private String lockModel;
    private boolean locked;
    private LocalDateTime lastUnlocked;
    private static final Logger logger =  LogManager.getLogger(SmartDoorLock.class);

    public SmartDoorLock(String name, BigDecimal price, LocalDate installedDate, String lockModel) {
        super(name, price, installedDate);
        this.lockModel = lockModel;
        this.locked = true;
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
