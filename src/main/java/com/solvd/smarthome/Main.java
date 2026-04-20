package com.solvd.smarthome;

import com.solvd.smarthome.district.*;
import com.solvd.smarthome.district.house.Owner;
import com.solvd.smarthome.district.house.SmartHome;
import com.solvd.smarthome.district.house.SolarPanel;
import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.district.house.devices.DeviceGroup;
import com.solvd.smarthome.district.house.devices.DeviceZone;
import com.solvd.smarthome.district.house.devices.climatedevice.SmartAirConditioner;
import com.solvd.smarthome.district.house.devices.climatedevice.Thermostat;
import com.solvd.smarthome.district.house.devices.sensors.MotionSensor;
import com.solvd.smarthome.district.house.devices.sensors.SecurityCamera;
import com.solvd.smarthome.district.house.devices.smartdevices.*;
import com.solvd.smarthome.district.house.rooms.*;
import com.solvd.smarthome.enums.AlertLevel;
import com.solvd.smarthome.enums.ConnectionProtocol;
import com.solvd.smarthome.enums.DeviceStatus;
import com.solvd.smarthome.enums.EnergyRating;
import com.solvd.smarthome.lambdas.DeviceService;
import com.solvd.smarthome.reflection.ReflectionDemo;
import com.solvd.smarthome.threads.CompletableFutureDemo;
import com.solvd.smarthome.threads.ThreadDemo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    @SafeVarargs
    private static <T> List<T> list(T... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static void main(String[] args) throws Exception {

        List<DeviceGroup> deviceGroups = new ArrayList<>();

        SmartLight ceilingLight = new SmartLight("Ceiling Light", new BigDecimal("49.99"),
                LocalDate.of(2024, 3, 10), "Warm White", "Philips Hue", 80, true);

        SmartLight floorLamp = new SmartLight("Floor Lamp", new BigDecimal("29.99"),
                LocalDate.of(2024, 3, 10), "Soft White", "Ikea Tradfri", 60, true);

        SmartTV livingRoomTV = new SmartTV("Living Room TV", new BigDecimal("799.99"),
                LocalDate.of(2023, 11, 20), "Samsung", 55, true);

        SmartSpeaker livingRoomSpeaker = new SmartSpeaker("Living Room Speaker",
                new BigDecimal("129.99"), LocalDate.of(2024, 1, 5), "Alexa", 5, 20, true);

        Thermostat livingRoomThermostat = new Thermostat("Living Room Thermostat",
                new BigDecimal("89.99"), LocalDate.of(2023, 12, 1), 22.0, 24.0, 100.0);

        SmartAirConditioner livingRoomAC = new SmartAirConditioner("Living Room AC",
                new BigDecimal("349.99"), LocalDate.of(2023, 6, 15), "Cool", 21.0, 3, 26.7);

        MotionSensor hallwaySensor = new MotionSensor("Hallway Motion Sensor",
                new BigDecimal("39.99"), LocalDate.of(2024, 2, 14), 7, 10, 67.8, "Super sensitive");

        SmartDoorLock mainDoorLock = new SmartDoorLock("Main Door Lock",
                new BigDecimal("199.99"), LocalDate.of(2024, 1, 20), "Yale Assure");

        SecurityCamera frontDoorCamera = new SecurityCamera("Front Door Camera",
                new BigDecimal("149.99"), LocalDate.of(2024, 2, 1), "4K", "Infrared", 56.4);

        SmartPlug coffeeMachinePlug = new SmartPlug("Coffee Machine Plug",
                new BigDecimal("24.99"), LocalDate.of(2024, 3, 5), "Coffee Machine",
                new BigDecimal("1200.00"), false);

        SmartRouter officeRouter = new SmartRouter("Office Router",
                new BigDecimal("189.99"), LocalDate.of(2023, 10, 10),
                "MySmartHome_5G", 12, 2.4, false);

        Owner owner = new Owner("John Smith", "john@example.com", "+1-555-9999",
                LocalDate.of(2022, 5, 1));

        Neighbourhood neighbourhood = new Neighbourhood("Maple Grove", "Springfield", 120, true);

        NetworkProvider networkProvider = new NetworkProvider("SpeedNet ISP",
                new BigDecimal("59.99"), 500, "Fiber");

        SecurityCompany securityCompany = new SecurityCompany("ShieldGuard",
                "+1-555-0911", new BigDecimal("39.99"), true);

        SolarPanel solarPanel = new SolarPanel("SunPower",
                new BigDecimal("8.50"), 12, LocalDate.of(2022, 7, 15));

        SewerageSystem sewerageSystem = new SewerageSystem("City Sewerage Co.",
                true, LocalDate.of(2024, 9, 10), "PVC");

        Garage garage = new Garage("Garage", new BigDecimal("50.0"), deviceGroups, 2);
        Garden garden = new Garden("Garden", new BigDecimal("120.00"), deviceGroups, true);
        HomeGym homeGym = new HomeGym("Home Gym", new BigDecimal("30.00"), deviceGroups, 10);

        DeviceZone northLighting = new DeviceZone("North Lighting", "North Wall");
        DeviceZone southLighting = new DeviceZone("South Lighting", "South Corner");
        DeviceZone mainEntertainment = new DeviceZone("Main Entertainment", "East Wall");

        DeviceGroup livingLighting = new DeviceGroup("Living Lighting", "Lighting",
                list(northLighting, southLighting));

        DeviceGroup livingEntertainment = new DeviceGroup("Entertainment", "Entertainment",
                list(mainEntertainment));

        Room livingRoom = new Room("Living Room", new BigDecimal("35.00"),
                list(livingLighting, livingEntertainment));

        Floor groundFloor = new Floor(1, "Ground Floor", list(livingRoom));

        SmartHome myHome = new SmartHome("My Smart Home", LocalDate.of(2023, 6, 1),
                owner, neighbourhood, networkProvider, securityCompany,
                solarPanel, sewerageSystem, garage, garden, homeGym,
                list(groundFloor));

        Street mainStreet = new Street("123 Main Street", "62701", list(myHome));
        District district = new District("Westside", "Springfield", "USA", list(mainStreet));

        logger.info(district.listAllDevices());
        logger.info(mainStreet.streetInfo());
        logger.info(myHome.listAllDevices());

        logger.info("Total smart homes: {}", SmartHome.getTotalHomes());
        logger.info("Total devices: {}", Device.getDeviceCount());

        Device[] allDevices = {
                ceilingLight, floorLamp, livingRoomTV, livingRoomSpeaker,
                livingRoomThermostat, livingRoomAC, hallwaySensor,
                mainDoorLock, frontDoorCamera, coffeeMachinePlug, officeRouter
        };

        logger.info("Running health check...");
        DeviceService service = new DeviceService();

        service.scheduleHealthCheck(() ->
                logger.info("Pinging {} devices", allDevices.length));

        Supplier<String> greeting = () ->
                "Hello " + owner.getName() + ", devices: " + allDevices.length;

        logger.info(service.generateSystemMessage(greeting));

        Consumer<Device> printer = d -> logger.info("Device: {}", d.deviceInfo());
        service.processAllDevices(allDevices, printer);

        Predicate<Device> expensive = d -> d.getPrice().compareTo(new BigDecimal("100")) > 0;
        Device[] filtered = service.filterDevices(allDevices, expensive);

        logger.info("Expensive devices count: {}", filtered.length);

        BiConsumer<Device, AlertLevel> alert = (d, l) ->
                logger.warn("ALERT [{}] {}", l, l.dispatchMessage(d.getName()));

        service.triggerAlert(frontDoorCamera, AlertLevel.CRITICAL, alert);

        logger.info("Enum demo starting...");
        logger.info("DeviceStatus: {}", DeviceStatus.ONLINE);

        logger.info("Energy rating: {}", EnergyRating.recommend(300));

        logger.info("Connection fastest: {}", ConnectionProtocol.fastest());

        logger.info("Stream total cost: {}",
                Arrays.stream(allDevices)
                        .map(Device::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add));

        try {
            SmartLight reflected = ReflectionDemo.createSmartLightViaReflection();
            logger.info("Reflected device: {}", reflected.getName());
        } catch (Exception e) {
            logger.error("Reflection error", e);
        }

        logger.info("[DONE] System simulation finished successfully.");


        ThreadDemo.runThreadExamples();

        ThreadDemo.runConnectionPoolDemo();

        CompletableFutureDemo.runAll();

    }
}