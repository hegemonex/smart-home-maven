package com.solvd.smarthome.district;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class SecurityCompany {

    private String name;
    private String contactNumber;
    private BigDecimal monthlyFee;
    private boolean armed;

    public SecurityCompany(String name, String contactNumber, BigDecimal monthlyFee, boolean armed) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.monthlyFee = monthlyFee;
        this.armed = armed;
    }

    public SecurityCompany() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public boolean isArmed() {
        return armed;
    }

    public void setArmed(boolean armed) {
        this.armed = armed;
    }

    public String securityInfo() {
        return "Security Company: " + name + " | Contact: " + contactNumber + " | Fee: $" + monthlyFee + "/month | Armed: " + armed;
    }
}
