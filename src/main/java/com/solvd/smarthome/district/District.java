package com.solvd.smarthome.district;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class District {

    private String districtName;
    private String city;
    private String country;
    private final Map<String, Street> streetMap;

    public District(String districtName, String city, String country, List<Street> streets) {
        this.districtName = districtName;
        this.city = city;
        this.country = country;

        this.streetMap = new LinkedHashMap<>();
        if (streets != null) {
            for (Street street : streets) {
                streetMap.put(street.getStreetName(), street);
            }
        }
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void addStreet(Street street) {
        if (street != null) streetMap.put(street.getStreetName(), street);
    }

    public Street removeStreet(String streetName) {
        return streetMap.remove(streetName);
    }

    public Street getStreet(String streetName) {
        return streetMap.get(streetName);
    }

    public boolean isEmpty() {
        return streetMap.isEmpty();
    }

    public int size() {
        return streetMap.size();
    }

    public Street getFirstStreet() {
        return streetMap.isEmpty() ? null : streetMap.values().iterator().next();
    }

    public String listAllDevices() {
        if (streetMap.isEmpty()) {
            return districtName + " (" + city + ", " + country + "): no streets";
        }

        String streets = streetMap.entrySet().stream()
                .map(e -> e.getKey() + " → " + e.getValue().streetInfo())
                .collect(Collectors.joining("\n"));

        return "District: " + districtName + " (" + city + ", " + country + ")\n" + streets + "\n";
    }
}