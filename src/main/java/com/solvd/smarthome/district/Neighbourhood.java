package com.solvd.smarthome.district;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Neighbourhood {

    private String name;
    private String city;
    private int totalHouses;
    private boolean hasSecurityGate;

    public Neighbourhood(String name, String city, int totalHouses, boolean hasSecurityGate) {
        this.name = name;
        this.city = city;
        this.totalHouses = totalHouses;
        this.hasSecurityGate = hasSecurityGate;
    }

    public Neighbourhood() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTotalHouses() {
        return totalHouses;
    }

    public void setTotalHouses(int totalHouses) {
        this.totalHouses = totalHouses;
    }

    public boolean isHasSecurityGate() {
        return hasSecurityGate;
    }

    public void setHasSecurityGate(boolean hasSecurityGate) {
        this.hasSecurityGate = hasSecurityGate;
    }

    public String neighbourhoodInfo() {
        return "Neighbourhood: " + name + " | City: " + city + " | Houses: " + totalHouses + " | Security Gate: " + hasSecurityGate;
    }
}