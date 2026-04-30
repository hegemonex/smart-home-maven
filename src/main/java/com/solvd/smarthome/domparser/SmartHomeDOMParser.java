package com.solvd.smarthome.domparser;

import com.solvd.smarthome.district.*;
import com.solvd.smarthome.district.house.*;
import com.solvd.smarthome.district.house.rooms.*;
import com.solvd.smarthome.district.house.devices.*;

import org.w3c.dom.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
/**
 * SmartHomeDOMParser is responsible for parsing SmartHome XML
 * using DOM and reconstructing the full object hierarchy.
 *
 * This class also demonstrates XPath usage for querying XML nodes.
 *
 * Example XPath expressions used in SmartHome XML:
 *
 * 1. /smartHome/name
 *    → Retrieves the name of the SmartHome
 *
 * 2. /smartHome/builtDate
 *    → Retrieves the built date of the SmartHome
 *
 * 3. /smartHome/neighbourhood/city
 *    → Retrieves the city of the neighbourhood
 *
 * 4. //owner/name
 *    → Retrieves all owner names in the system
 *
 * 5. //devicegroup/groupName
 *    → Retrieves all device group names across rooms
 *
 * 6. /smartHome/floors/floor/rooms/room/name
 *    → Retrieves all room names in all floors
 */
public class SmartHomeDOMParser {

    public static SmartHome parse(Document document) {

        document.getDocumentElement().normalize();

        String name = getText(document, "name");
        LocalDate builtDate = LocalDate.parse(getText(document, "builtDate"));

        Element n = (Element) document.getElementsByTagName("neighbourhood").item(0);

        Neighbourhood neighbourhood = new Neighbourhood(
                getText(n, "name"),
                getText(n, "city"),
                Integer.parseInt(getText(n, "totalHouses")),
                Boolean.parseBoolean(getText(n, "hasSecurityGate"))
        );

        Element np = (Element) document.getElementsByTagName("networkProvider").item(0);

        NetworkProvider networkProvider = new NetworkProvider(
                getText(np, "companyName"),
                new BigDecimal(getText(np, "monthlyFee")),
                Integer.parseInt(getText(np, "speedMbps")),
                getText(np, "connectionType")
        );

        Element sc = (Element) document.getElementsByTagName("securityCompany").item(0);

        SecurityCompany securityCompany = new SecurityCompany(
                getText(sc, "name"),
                getText(sc, "contactNumber"),
                new BigDecimal(getText(sc, "monthlyFee")),
                Boolean.parseBoolean(getText(sc, "armed"))
        );

        Element sp = (Element) document.getElementsByTagName("solarPanel").item(0);

        SolarPanel solarPanel = new SolarPanel(
                getText(sp, "manufacturer"),
                new BigDecimal(getText(sp, "outputKw")),
                Integer.parseInt(getText(sp, "panelCount")),
                LocalDate.parse(getText(sp, "installedDate"))
        );

        Element ss = (Element) document.getElementsByTagName("sewerageSystem").item(0);

        SewerageSystem sewerageSystem = new SewerageSystem(
                getText(ss, "provider"),
                Boolean.parseBoolean(getText(ss, "connected")),
                LocalDate.parse(getText(ss, "lastInspected")),
                getText(ss, "pipeType")
        );

        NodeList ownerNodes = document.getElementsByTagName("owner");

        Set<Owner> owners = new HashSet<>();

        for (int i = 0; i < ownerNodes.getLength(); i++) {
            Element o = (Element) ownerNodes.item(i);

            owners.add(new Owner(
                    getText(o, "name"),
                    getText(o, "email"),
                    getText(o, "phone"),
                    LocalDate.parse(getText(o, "memberSince"))
            ));
        }

        Owner primaryOwner = owners.iterator().next();

        List<Floor> floors = new ArrayList<>();

        NodeList floorNodes = document.getElementsByTagName("floor");

        for (int i = 0; i < floorNodes.getLength(); i++) {

            Element f = (Element) floorNodes.item(i);

            List<Room> rooms = new ArrayList<>();

            NodeList roomNodes = f.getElementsByTagName("room");

            for (int j = 0; j < roomNodes.getLength(); j++) {

                Element r = (Element) roomNodes.item(j);

                List<DeviceGroup> deviceGroups = new ArrayList<>();

                NodeList groupNodes = r.getElementsByTagName("devicegroup");

                for (int k = 0; k < groupNodes.getLength(); k++) {

                    Element g = (Element) groupNodes.item(k);

                    List<DeviceZone> zones = new ArrayList<>();

                    NodeList zoneNodes = g.getElementsByTagName("devicezone");

                    for (int z = 0; z < zoneNodes.getLength(); z++) {

                        Element zn = (Element) zoneNodes.item(z);

                        zones.add(new DeviceZone(
                                getText(zn, "zoneName"),
                                getText(zn, "location")
                        ));
                    }

                    deviceGroups.add(new DeviceGroup(
                            getText(g, "groupName"),
                            getText(g, "category"),
                            zones
                    ));
                }

                rooms.add(new Room(
                        getText(r, "name"),
                        new BigDecimal(getText(r, "area")),
                        deviceGroups
                ));
            }

            floors.add(new Floor(
                    Integer.parseInt(getText(f, "floorNumber")),
                    getText(f, "label"),
                    rooms
            ));
        }

        return new SmartHome(
                name,
                builtDate,
                primaryOwner,
                neighbourhood,
                networkProvider,
                securityCompany,
                solarPanel,
                sewerageSystem,
                new Garage("Garage", BigDecimal.ZERO, new ArrayList<>(), 2),
                new Garden("Garden", BigDecimal.ZERO, new ArrayList<>(), true),
                new HomeGym("Home Gym", BigDecimal.ZERO, new ArrayList<>(), 10),
                floors
        );
    }

    // ================= HELPER =================
    private static String getText(Node node, String tag) {
        return ((Element) node).getElementsByTagName(tag)
                .item(0)
                .getTextContent();
    }

    private static String getText(Document doc, String tag) {
        return doc.getElementsByTagName(tag)
                .item(0)
                .getTextContent();
    }
}