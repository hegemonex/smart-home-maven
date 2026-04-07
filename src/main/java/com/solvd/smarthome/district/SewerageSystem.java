package com.solvd.smarthome.district;

import java.time.LocalDate;

public class SewerageSystem {

    private String provider;
    private boolean connected;
    private LocalDate lastInspected;
    private String pipeType;

    public SewerageSystem(String provider, boolean connected, LocalDate lastInspected, String pipeType) {
        this.provider = provider;
        this.connected = connected;
        this.lastInspected = lastInspected;
        this.pipeType = pipeType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public LocalDate getLastInspected() {
        return lastInspected;
    }

    public void setLastInspected(LocalDate lastInspected) {
        this.lastInspected = lastInspected;
    }

    public String getPipeType() {
        return pipeType;
    }

    public void setPipeType(String pipeType) {
        this.pipeType = pipeType;
    }

    public String sewerageInfo() {
        return "Sewerage | Provider: " + provider + " | Connected: " + connected + " | Pipe: " + pipeType + " | Last Inspected: " + lastInspected;
    }
}