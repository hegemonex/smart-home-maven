package com.solvd.smarthome.district;

import java.math.BigDecimal;

public class NetworkProvider {

    private String companyName;
    private BigDecimal monthlyFee;
    private int speedMbps;
    private String connectionType;

    public NetworkProvider(String companyName, BigDecimal monthlyFee, int speedMbps, String connectionType) {
        this.companyName = companyName;
        this.monthlyFee = monthlyFee;
        this.speedMbps = speedMbps;
        this.connectionType = connectionType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public int getSpeedMbps() {
        return speedMbps;
    }

    public void setSpeedMbps(int speedMbps) {
        this.speedMbps = speedMbps;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String networkInfo() {
        return "Network Provider: " + companyName + " | Speed: " + speedMbps + " Mbps | Type: " + connectionType + " | Fee: $" + monthlyFee + "/month";
    }
}