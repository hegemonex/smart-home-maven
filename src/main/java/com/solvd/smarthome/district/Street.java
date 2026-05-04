package com.solvd.smarthome.district;

import com.solvd.smarthome.district.house.SmartHome;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Street {

    private String streetName;
    private String postalCode;

    @XmlElementWrapper(name = "homes")
    @XmlElement(name = "smarthomes")
    private List<SmartHome> homes;

    public Street(String streetName, String postalCode, List<SmartHome> homes) {
        this.homes = homes != null ? new ArrayList<>(homes) : new ArrayList<>();
    }

    public Street() {
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void addHome(SmartHome home) {
        homes.add(home);
    }

    public boolean removeHome(SmartHome h) {
        return homes.remove(h);
    }

    public boolean isEmpty() {
        return homes.isEmpty();
    }

    public int size() {
        return homes.size();
    }

    public SmartHome getFirstHome() {
        return homes.isEmpty() ? null : homes.get(0);
    }

    public String streetInfo() {
        return "Street: " + streetName + " | Postal Code: " + postalCode + " | Homes: " + (homes != null ? homes.size() : 0);
    }
}