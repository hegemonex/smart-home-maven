package com.solvd.smarthome.district.house.devices.smartdevices;


import com.solvd.smarthome.district.house.devices.Connectable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SmartRouter extends SmartDevice implements Connectable {

    private String ssid;
    private int connectedDevicesCount;
    private double bandwidthGbps;
    private static final Logger logger = LogManager.getLogger(SmartRouter.class);

    public SmartRouter(String name, BigDecimal price, LocalDate installedDate, String ssid, int connectedDevicesCount, double bandwidthGbps, boolean connected) {
        super(name, price, installedDate, connected);
        this.ssid = ssid;
        this.connectedDevicesCount = connectedDevicesCount;
        this.bandwidthGbps = bandwidthGbps;
    }

    public SmartRouter() {
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public int getConnectedDevicesCount() {
        return connectedDevicesCount;
    }

    public void setConnectedDevicesCount(int connectedDevicesCount) {
        this.connectedDevicesCount = connectedDevicesCount;
    }

    public double getBandwidthGbps() {
        return bandwidthGbps;
    }

    public void setBandwidthGbps(double bandwidthGbps) {
        this.bandwidthGbps = bandwidthGbps;
    }

    @Override
    public String deviceInfo() {
        return super.deviceInfo() + " | SSID: " + ssid + ", Connected: " + connectedDevicesCount + " devices, Bandwidth: " + bandwidthGbps + " Gbps";
    }

    public String showNetworkStatus() {
        return getName() + ": broadcasting '" + ssid + "' with " + connectedDevicesCount + " device(s) connected.";
    }

    public String reboot() {
        return getName() + " is rebooting... Network will resume shortly.";
    }

    @Override
    public void operate() {
        logger.info("{} is routering", name);
    }

    @Override
    public void connect() {
        logger.info("{} is connecting", name);
    }

    public boolean startRouter() {
        connectedToWifi = true;
        return true;
    }

    @Override
    public void disconnect() {
        logger.info("{} is disconnecting", name);
    }
}