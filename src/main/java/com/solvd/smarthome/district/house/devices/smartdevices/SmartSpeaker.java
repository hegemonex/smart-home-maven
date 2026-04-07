package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Connectable;
import com.solvd.smarthome.district.house.devices.Switchable;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SmartSpeaker extends SmartDevice implements Connectable, Switchable {

    private String assistantName;
    private int volume;
    private int maxVolume;

    public SmartSpeaker(String name, BigDecimal price, LocalDate installedDate, String assistantName, int volume, int maxVolume, boolean connected) {
        super(name, price, installedDate, connected);
        this.assistantName = assistantName;
        this.volume = volume;
        this.maxVolume = maxVolume;
    }

    public String getAssistantName() {
        return assistantName;
    }

    public void setAssistantName(String assistantName) {
        this.assistantName = assistantName;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(int maxVolume) {
        this.maxVolume = maxVolume;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | Assistant: " + assistantName + ", Volume: " + volume + "/" + maxVolume;
    }

    public String turnUpVol() {
        if (volume < maxVolume) {
            volume++;
        }
        return getName() + " volume turned up to " + volume;
    }

    public String turnDownVol() {
        if (volume > 0) {
            volume--;
        }
        return getName() + " volume turned down to " + volume;
    }

    @Override
    public void operate() {
        System.out.println(name + " is speaking");
    }

    @Override
    public void connect() {
        System.out.println(name + " is speaking");
    }

    @Override
    public void disconnect() {
        System.out.println(name + " is speaking");
    }

    @Override
    public void switchOn() {
        System.out.println(name + " is speaking");
    }

    @Override
    public void switchOff() {
        System.out.println(name + " is speaking");
    }
}