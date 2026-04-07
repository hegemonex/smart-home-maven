package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Connectable;
import com.solvd.smarthome.district.house.devices.Device;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SmartDoorLock extends Device implements Connectable {

    private String lockModel;
    private boolean locked;
    private LocalDateTime lastUnlocked;

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
        System.out.println(name + " is locking");
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
