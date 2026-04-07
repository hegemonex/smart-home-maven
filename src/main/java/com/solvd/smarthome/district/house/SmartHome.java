package com.solvd.smarthome.district.house;

import com.solvd.smarthome.district.Neighbourhood;
import com.solvd.smarthome.district.NetworkProvider;
import com.solvd.smarthome.district.SecurityCompany;
import com.solvd.smarthome.district.SewerageSystem;
import com.solvd.smarthome.district.house.rooms.Floor;
import com.solvd.smarthome.district.house.rooms.Garage;
import com.solvd.smarthome.district.house.rooms.Garden;
import com.solvd.smarthome.district.house.rooms.HomeGym;
import com.solvd.smarthome.util.Pair;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SmartHome {

    private static final String SYSTEM_VERSION = "2.0";
    private static int totalHomes = 0;

    static {
        System.out.println("SmartHome system initialized. Version: " + SYSTEM_VERSION);
    }

    private final String name;
    private final LocalDate builtDate;
    private final Neighbourhood neighbourhood;
    private final NetworkProvider networkProvider;
    private final SecurityCompany securityCompany;
    private final SolarPanel solarPanel;
    private final SewerageSystem sewerageSystem;
    private final Garage garage;
    private final Garden garden;
    private final HomeGym homeGym;
    private final Set<Owner> owners;
    private final List<Floor> floors;

    public SmartHome(String name, LocalDate builtDate, Owner primaryOwner,
                     Neighbourhood neighbourhood, NetworkProvider networkProvider,
                     SecurityCompany securityCompany, SolarPanel solarPanel,
                     SewerageSystem sewerageSystem, Garage garage,
                     Garden garden, HomeGym homeGym, List<Floor> floors) {

        this.name = name;
        this.builtDate = builtDate;
        this.neighbourhood = neighbourhood;
        this.networkProvider = networkProvider;
        this.securityCompany = securityCompany;
        this.solarPanel = solarPanel;
        this.sewerageSystem = sewerageSystem;
        this.garage = garage;
        this.garden = garden;
        this.homeGym = homeGym;

        this.owners = new HashSet<>();
        if (primaryOwner != null) {
            this.owners.add(primaryOwner);
        }

        this.floors = floors != null ? new ArrayList<>(floors) : new ArrayList<>();

        totalHomes++;
    }

    public static int getTotalHomes() {
        return totalHomes;
    }

    public static String getSystemVersion() {
        return SYSTEM_VERSION;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBuiltDate() {
        return builtDate;
    }

    public void addOwner(Owner owner) {
        if (owner != null) {
            owners.add(owner);
        }
    }

    public boolean removeOwner(Owner owner) {
        return owners.remove(owner);
    }

    public Owner getPrimaryOwner() {
        return owners.isEmpty() ? null : owners.iterator().next();
    }

    public String listAllDevices() {
        if (floors.isEmpty()) {
            return name + " has no floors set up yet.";
        }

        String ownersList = owners.stream()
                .map(owner -> "  - " + owner.ownerInfo())
                .collect(Collectors.joining("\n"));

        String floorsList = floors.stream()
                .map(Floor::listRooms)
                .collect(Collectors.joining("\n"));

        return "=== " + name + " (built: " + builtDate + ") ===\n"
                + "Owners:\n" + ownersList + "\n"
                + "\nDevices:\n" + floorsList + "\n";
    }

    public Pair<String, String> getHomeOwnerSummary() {
        Owner primary = getPrimaryOwner();
        return new Pair<>(name, primary != null ? primary.getName() : "Unknown");
    }
}