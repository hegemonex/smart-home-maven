package com.solvd.smarthome.district.house.devices.smartdevices;

import com.solvd.smarthome.district.house.devices.Connectable;
import com.solvd.smarthome.district.house.devices.Switchable;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class SmartSpeaker extends SmartDevice implements Connectable, Switchable {

    private String assistantName;
    private int volume;
    private int maxVolume;
    private static Logger logger = LogManager.getLogger(SmartSpeaker.class);


    public SmartSpeaker(String name, BigDecimal price, LocalDate installedDate, String assistantName, int volume, int maxVolume, boolean connected) {
        super(name, price, installedDate, connected);
        this.assistantName = assistantName;
        this.volume = volume;
        this.maxVolume = maxVolume;
    }

    public SmartSpeaker() {
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
        logger.info("Operating SmartSpeaker");
    }

    @Override
    public void connect() {
        logger.info("Connecting SmartSpeaker");
    }

    @Override
    public void disconnect() {
        logger.info("Disconnecting SmartSpeaker");
    }

    @Override
    public void switchOn() {
        logger.info("Switching On SmartSpeaker");
    }

    @Override
    public void switchOff() {
        logger.info("Switching Off SmartSpeaker");
    }
}