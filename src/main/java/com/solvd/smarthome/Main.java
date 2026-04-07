package com.solvd.smarthome;

import com.solvd.smarthome.district.District;
import com.solvd.smarthome.district.Street;
import com.solvd.smarthome.district.*;
import com.solvd.smarthome.district.house.Owner;
import com.solvd.smarthome.district.house.SmartHome;
import com.solvd.smarthome.district.house.SmartHomeLogger;
import com.solvd.smarthome.district.house.SolarPanel;
import com.solvd.smarthome.district.house.devices.Connectable;
import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.district.house.devices.DeviceGroup;
import com.solvd.smarthome.district.house.devices.DeviceZone;
import com.solvd.smarthome.district.house.devices.climatedevice.SmartAirConditioner;
import com.solvd.smarthome.district.house.devices.climatedevice.Thermostat;
import com.solvd.smarthome.district.house.devices.sensors.MotionSensor;
import com.solvd.smarthome.district.house.devices.sensors.SecurityCamera;
import com.solvd.smarthome.district.house.devices.smartdevices.*;
import com.solvd.smarthome.district.house.rooms.*;
import com.solvd.smarthome.enums.*;
import com.solvd.smarthome.exceptions.DeviceInstallationException;
import com.solvd.smarthome.lambdas.AlertHandler;
import com.solvd.smarthome.lambdas.DeviceAction;
import com.solvd.smarthome.lambdas.DeviceFilter;
import com.solvd.smarthome.lambdas.DeviceService;
import com.solvd.smarthome.records.DeviceRecord;
import com.solvd.smarthome.reflection.ReflectionDemo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    @SafeVarargs
    private static <T> List<T> list(T... items) {
        List<T> result = new ArrayList<>();
        for (T item : items) result.add(item);
        return result;
    }

    public static void main(String[] args) {

        List<DeviceGroup> deviceGroups = new ArrayList<>();

        SmartLight ceilingLight = new SmartLight("Ceiling Light", new BigDecimal("49.99"), LocalDate.of(2024, 3, 10), "Warm White", "Philips Hue", 80, true);
        SmartLight floorLamp = new SmartLight("Floor Lamp", new BigDecimal("29.99"), LocalDate.of(2024, 3, 10), "Soft White", "Ikea Tradfri", 60, true);
        SmartTV livingRoomTV = new SmartTV("Living Room TV", new BigDecimal("799.99"), LocalDate.of(2023, 11, 20), "Samsung", 55, true);
        SmartSpeaker livingRoomSpeaker = new SmartSpeaker("Living Room Speaker", new BigDecimal("129.99"), LocalDate.of(2024, 1, 5), "Alexa", 5, 20, true);
        Thermostat livingRoomThermostat = new Thermostat("Living Room Thermostat", new BigDecimal("89.99"), LocalDate.of(2023, 12, 1), 22.0, 24.0, 100.0);
        SmartAirConditioner livingRoomAC = new SmartAirConditioner("Living Room AC", new BigDecimal("349.99"), LocalDate.of(2023, 6, 15), "Cool", 21.0, 3, 26.7);
        MotionSensor hallwaySensor = new MotionSensor("Hallway Motion Sensor", new BigDecimal("39.99"), LocalDate.of(2024, 2, 14), 7, 10, 67.8, "Super sensitive");
        SmartDoorLock mainDoorLock = new SmartDoorLock("com.solvd.smarthome.com.solvd.smarthome.Main Door Lock", new BigDecimal("199.99"), LocalDate.of(2024, 1, 20), "Yale Assure");
        SecurityCamera frontDoorCamera = new SecurityCamera("Front Door Camera", new BigDecimal("149.99"), LocalDate.of(2024, 2, 1), "4K", "Infrared", 56.4);
        SmartPlug coffeeMachinePlug = new SmartPlug("Coffee Machine Plug", new BigDecimal("24.99"), LocalDate.of(2024, 3, 5), "Coffee Machine", new BigDecimal("1200.00"), false);
        SmartRouter officeRouter = new SmartRouter("Office Router", new BigDecimal("189.99"), LocalDate.of(2023, 10, 10), "MySmartHome_5G", 12, 2.4, false);

        Owner owner = new Owner("John Smith", "john@example.com", "+1-555-9999", LocalDate.of(2022, 5, 1));
        Neighbourhood neighbourhood = new Neighbourhood("Maple Grove", "Springfield", 120, true);
        NetworkProvider networkProvider = new NetworkProvider("SpeedNet ISP", new BigDecimal("59.99"), 500, "Fiber");
        SecurityCompany securityCompany = new SecurityCompany("ShieldGuard", "+1-555-0911", new BigDecimal("39.99"), true);
        SolarPanel solarPanel = new SolarPanel("SunPower", new BigDecimal("8.50"), 12, LocalDate.of(2022, 7, 15));
        SewerageSystem sewerageSystem = new SewerageSystem("City Sewerage Co.", true, LocalDate.of(2024, 9, 10), "PVC");

        Garage garage = new Garage("Garage", new BigDecimal("50.0"), deviceGroups, 2);
        Garden garden = new Garden("Garden", new BigDecimal("120.00"), deviceGroups, true);
        HomeGym homeGym = new HomeGym("Home Gym", new BigDecimal("30.00"), deviceGroups, 10);

        DeviceZone northLighting = new DeviceZone("North Lighting", "North Wall");
        DeviceZone southLighting = new DeviceZone("South Lighting", "South Corner");
        DeviceZone mainEntertainment = new DeviceZone("com.solvd.smarthome.com.solvd.smarthome.Main Entertainment", "East Wall");
        DeviceZone climateControl = new DeviceZone("Climate Control", "Central");
        DeviceZone entrySecurity = new DeviceZone("Entry Security", "Front Door");
        DeviceZone motionDetection = new DeviceZone("Motion Detection", "Ceiling");
        DeviceZone kitchenAppliances = new DeviceZone("Appliances", "Counter");
        DeviceZone networkZone = new DeviceZone("Network", "Desk");

        DeviceGroup livingLighting = new DeviceGroup("Living Lighting", "Lighting", list(northLighting, southLighting));
        DeviceGroup livingEntertainment = new DeviceGroup("Entertainment", "Entertainment", list(mainEntertainment));
        DeviceGroup livingClimate = new DeviceGroup("Climate", "Climate", list(climateControl));
        DeviceGroup hallwaySecurity = new DeviceGroup("Security", "Security", list(entrySecurity, motionDetection));
        DeviceGroup kitchenGroup = new DeviceGroup("Kitchen Devices", "Appliances", list(kitchenAppliances));
        DeviceGroup officeNetwork = new DeviceGroup("Network", "Network", list(networkZone));

        Room livingRoom = new Room("Living Room", new BigDecimal("35.00"), list(livingLighting, livingEntertainment, livingClimate));
        Room hallway = new Room("Hallway", new BigDecimal("12.00"), list(hallwaySecurity));
        Room kitchen = new Room("Kitchen", new BigDecimal("20.00"), list(kitchenGroup));
        Room office = new Room("Office", new BigDecimal("18.00"), list(officeNetwork));

        Floor groundFloor = new Floor(1, "Ground Floor", list(livingRoom, hallway, kitchen));
        Floor firstFloor = new Floor(2, "First Floor", list(office));

        SmartHome myHome = new SmartHome("My Smart Home", LocalDate.of(2023, 6, 1), owner, neighbourhood, networkProvider, securityCompany, solarPanel, sewerageSystem, garage, garden, homeGym, list(groundFloor, firstFloor));

        Street mainStreet = new Street("123 com.solvd.smarthome.com.solvd.smarthome.Main Street", "62701", list(myHome));
        District district = new District("Westside", "Springfield", "USA", list(mainStreet));

        System.out.println(district.listAllDevices());
        System.out.println(mainStreet.streetInfo());
        System.out.println(myHome.listAllDevices());

        System.out.println("Total smart homes: " + SmartHome.getTotalHomes());
        System.out.println("Total devices created: " + Device.getDeviceCount());

        Device device1 = new SmartLight("Bed Light", new BigDecimal("17.99"), LocalDate.of(2024, 3, 10), "cool blue", "Philips Hue", 20, false);
        Device device2 = new SmartTV("Bedroom TV", new BigDecimal("799.99"), LocalDate.of(2023, 11, 20), "Samsung", 55, true);
        Device device3 = new Thermostat("Bathroom Thermostat", new BigDecimal("89.99"), LocalDate.of(2023, 12, 1), 22.0, 24.0, 100.0);

        device1.operate();
        device2.operate();
        device3.operate();

        Connectable tv = new SmartTV("tv", new BigDecimal(455.0), LocalDate.of(2023, 11, 20), "Samsung", 55, true);
        Connectable router = new SmartRouter("router", new BigDecimal(56.9), LocalDate.of(2026, 10, 3), "fhriuhf", 5, 67.8, true);

        connectDevice(tv);
        connectDevice(router);

        try {
            Device device4 = new SmartLight();
            device4.setInstalledDate(null);
        } catch (DeviceInstallationException e) {
            System.out.println("there is no device installed");
        } finally {
            System.out.println("Instalation attempt finished");
        }

        try (SmartHomeLogger logger = new SmartHomeLogger()) {
            logger.log("Smart home system started");
        } catch (Exception e) {
            System.out.println("Logging error: " + e.getMessage());
        }

        SmartRouter router1 = new SmartRouter();
        router1.isConnectedToWifi();

        livingRoomThermostat.setTemperatureSetting(-50);

        System.out.println("\n── ENUM 1: DeviceStatus ──");

        DeviceStatus routerStatus = DeviceStatus.ONLINE;
        System.out.println("Router status : " + routerStatus);
        System.out.println("Is operational: " + routerStatus.isOperational());
        System.out.println("Status code   : " + routerStatus.getStatusCode());

        DeviceStatus looked = DeviceStatus.fromCode(4);
        System.out.println("Status for code 4: " + (looked != null ? looked.getDescription() : "unknown"));

        DeviceStatus cameraStatus = DeviceStatus.ERROR;
        if (!cameraStatus.isOperational()) {
            System.out.println("Front Door Camera needs attention — status: " + cameraStatus);
        }

        System.out.println("\n── ENUM 2: RoomType ──");

        for (RoomType type : RoomType.values()) {
            System.out.println(type.name() + ": " + type.describeFunction());
            System.out.println("  Capacity warning (15 devices): " + type.capacityWarning(15));
        }

        System.out.println("Room types on floor 1:");
        for (RoomType rt : RoomType.forFloor(1)) {
            System.out.println("  " + rt.name());
        }

        System.out.println("\n── ENUM 3: EnergyRating ──");

        EnergyRating tvRating = EnergyRating.recommend(290);
        System.out.println("TV energy rating      : " + tvRating.getLabel());
        System.out.println("Annual cost at $0.28  : $" + tvRating.annualCost(new BigDecimal("0.28")));
        System.out.println("Subsidy eligible      : " + tvRating.isSubsidyEligible());
        System.out.println("Annual saving vs D    : $" + tvRating.getAnnualSavingVsBaseline());

        System.out.println("\n── ENUM 4: AlertLevel ──");

        AlertLevel current = AlertLevel.WARNING;
        System.out.println("Current alert level  : " + current);
        AlertLevel escalated = current.escalate();
        System.out.println("After escalation     : " + escalated);
        System.out.println("Dispatch message     : " + escalated.dispatchMessage("Front Door Camera"));
        System.out.println("Immediate action req : " + escalated.requiresImmediateAction());

        System.out.println("\n── ENUM 5: ConnectionProtocol ──");

        ConnectionProtocol routerProtocol = ConnectionProtocol.WIFI_5;
        System.out.println("Router protocol   : " + routerProtocol);
        System.out.println("Handshake         : " + routerProtocol.connect("Office Router"));
        System.out.println("Long range?       : " + routerProtocol.isLongRange());
        System.out.println("Fastest protocol  : " + ConnectionProtocol.fastest());

        for (ConnectionProtocol p : ConnectionProtocol.values()) {
            System.out.println("  " + p.name() + " long-range=" + p.isLongRange());
        }

        System.out.println("\n── RECORD: DeviceRecord ──");

        DeviceRecord tvSnapshot = new DeviceRecord(
                "Living Room TV",
                "SmartTV",
                new BigDecimal("799.99"),
                LocalDate.of(2023, 11, 20),
                DeviceStatus.ONLINE,
                EnergyRating.recommend(350),
                ConnectionProtocol.WIFI_5,
                AlertLevel.INFO,
                LocalDateTime.now()
        );

        System.out.println("Record summary  : " + tvSnapshot.summary());
        System.out.println("Needs attention?: " + tvSnapshot.needsAttention());
        System.out.println("Device name     : " + tvSnapshot.deviceName());
        System.out.println("Energy rating   : " + tvSnapshot.energyRating().getLabel());
        System.out.println("Protocol        : " + tvSnapshot.protocol().name());

        DeviceRecord tvOffline = tvSnapshot.withStatus(DeviceStatus.OFFLINE);
        System.out.println("After going offline: " + tvOffline.summary());

        DeviceRecord cameraSnap = DeviceRecord.quickSnapshot(
                "Front Door Camera", "SecurityCamera",
                new BigDecimal("149.99"), LocalDate.of(2024, 2, 1));
        System.out.println("Camera snapshot : " + cameraSnap.summary());

        DeviceRecord alerted = cameraSnap.withEscalatedAlert();
        System.out.println("After escalation: " + alerted.summary());

        System.out.println("Full toString   : " + tvSnapshot);

        System.out.println("   LAMBDAS — java.com.solvd.smarthome.com.solvd.smarthome.util.function standard interfaces   ");

        DeviceService service = new DeviceService();

        Device[] allDevices = {
                ceilingLight, floorLamp, livingRoomTV, livingRoomSpeaker,
                livingRoomThermostat, livingRoomAC, hallwaySensor,
                mainDoorLock, frontDoorCamera, coffeeMachinePlug, officeRouter
        };

        System.out.println("\n[Runnable] Scheduling a health check:");

        Runnable healthCheck = () -> {
            System.out.println("  → Pinging all " + allDevices.length + " devices...");
            System.out.println("  → All devices responded within 50 ms.");
        };
        service.scheduleHealthCheck(healthCheck);

        service.scheduleHealthCheck(() -> System.out.println("  → Quick heartbeat ping OK."));

        System.out.println("\n[Supplier<String>] Generating a dynamic system message:");

        Supplier<String> morningGreeting = () ->
                "Good morning, " + owner.getName() + "! System ready. "
                        + "You have " + allDevices.length + " devices registered.";

        service.generateSystemMessage(morningGreeting);

        System.out.println("\n[Consumer<Device>] Printing device info for all devices:");

        Consumer<Device> infoPrinter = device ->
                System.out.println("  • " + device.deviceInfo());

        service.processAllDevices(allDevices, infoPrinter);

        System.out.println("\n[Function<Device,String>] Formatting a device for a log file:");

        Function<Device, String> logFormatter = device ->
                "[LOG " + LocalDate.now() + "] Device='" + device.getName()
                        + "' Price=$" + device.getPrice()
                        + " Installed=" + device.getInstalledDate();

        System.out.println("  " + service.formatDevice(livingRoomTV, logFormatter));

        Function<Device, String> labelFormatter = device ->
                "📺 " + device.getName() + " ($" + device.getPrice() + ")";

        System.out.println("  " + service.formatDevice(livingRoomTV, labelFormatter));

        System.out.println("\n[Predicate<Device>] Filtering expensive devices (> $100):");

        Predicate<Device> expensiveFilter = device ->
                device.getPrice().compareTo(new BigDecimal("100")) > 0;

        Device[] expensiveDevices = service.filterDevices(allDevices, expensiveFilter);
        System.out.println("  Devices over $100:");
        for (Device d : expensiveDevices) {
            System.out.println("    - " + d.getName() + " ($" + d.getPrice() + ")");
        }

        Predicate<Device> recentFilter = device ->
                device.getInstalledDate() != null
                        && device.getInstalledDate().isAfter(LocalDate.of(2024, 1, 1));

        Predicate<Device> expensiveAndRecent = expensiveFilter.and(recentFilter);
        Device[] premium = service.filterDevices(allDevices, expensiveAndRecent);
        System.out.println("  Expensive AND installed after 2024-01-01:");
        for (Device d : premium) {
            System.out.println("    - " + d.getName());
        }

        System.out.println("\n[BiFunction<Device,BigDecimal,String>] Cost analysis:");

        BiFunction<Device, BigDecimal, String> costAnalyser = (device, rate) -> {
            double roughKwh = device.getPrice().doubleValue() * 0.05;
            BigDecimal annualCost = rate.multiply(new BigDecimal(roughKwh * 365));
            return device.getName() + " — estimated annual cost: $"
                    + annualCost.setScale(2, java.math.RoundingMode.HALF_UP);
        };

        System.out.println("  " + service.analyseDeviceCost(livingRoomTV, new BigDecimal("0.28"), costAnalyser));
        System.out.println("  " + service.analyseDeviceCost(livingRoomAC, new BigDecimal("0.28"), costAnalyser));
        System.out.println("  " + service.analyseDeviceCost(officeRouter, new BigDecimal("0.28"), costAnalyser));

        System.out.println("\n[BiConsumer<Device,AlertLevel>] Triggering a security alert:");

        BiConsumer<Device, AlertLevel> alertLogger = (device, level) ->
                System.out.println("  ⚠ ALERT [" + level.name() + "] on '"
                        + device.getName() + "': " + level.dispatchMessage(device.getName()));

        service.triggerAlert(frontDoorCamera, AlertLevel.CRITICAL, alertLogger);
        service.triggerAlert(hallwaySensor, AlertLevel.WARNING, alertLogger);

        System.out.println("        CUSTOM FUNCTIONAL INTERFACES (3 types)        ");

        System.out.println("\n[DeviceFilter] Filter devices with domain-named interface:");

        DeviceFilter cheapFilter = device -> device.getPrice().doubleValue() <= 50;
        DeviceFilter wifiFilter = device -> device instanceof SmartDevice
                && ((SmartDevice) device).isConnectedToWifi();

        DeviceFilter cheapAndWifi = cheapFilter.and(wifiFilter);

        System.out.println("  Cheap devices (≤ $50):");
        for (Device d : service.applyFilter(allDevices, cheapFilter)) {
            System.out.println("    - " + d.getName() + " ($" + d.getPrice() + ")");
        }

        System.out.println("  Cheap AND connected to Wi-Fi:");
        for (Device d : service.applyFilter(allDevices, cheapAndWifi)) {
            System.out.println("    - " + d.getName());
        }

        System.out.println("\n[DeviceAction] Run a named action on all devices:");

        DeviceAction logAction = device -> "Logging: " + device.deviceInfo();
        DeviceAction operateAction = device -> {
            device.operate();
            return device.getName() + " operated successfully.";
        };

        DeviceAction logThenOperate = logAction.andThen(operateAction);

        Device[] smallSet = {ceilingLight, livingRoomTV, officeRouter};
        System.out.println("  Running logThenOperate on small set:");
        service.runActionOnAll(smallSet, logThenOperate);

        System.out.println("\n[AlertHandler] Composed alert handling pipeline:");

        AlertHandler logHandler = (name, level) ->
                System.out.println("  [LOG]  " + level.name() + " on device: " + name);

        AlertHandler pushHandler = (name, level) ->
                System.out.println("  [PUSH] Sending push notification: "
                        + level.dispatchMessage(name));

        AlertHandler smsHandler = (name, level) -> {
            if (level.getSeverity() >= AlertLevel.CRITICAL.getSeverity()) {
                System.out.println("  [SMS]  Texting owner: URGENT — " + name + " is " + level.name());
            }
        };

        AlertHandler fullPipeline = logHandler.andAlso(pushHandler).andAlso(smsHandler);

        service.dispatchAlert("Front Door Camera", AlertLevel.CRITICAL, fullPipeline);
        service.dispatchAlert("Hallway Sensor", AlertLevel.WARNING, fullPipeline);

        System.out.println("   INLINE LAMBDA DEMOS — all 7 interface types        ");

        Runnable rebootTask = () -> System.out.println("[Runnable] Rebooting all devices on Ground Floor...");
        rebootTask.run();

        Supplier<Integer> deviceCounter = () -> Device.getDeviceCount();
        System.out.println("[Supplier<Integer>] Total devices created so far: " + deviceCounter.get());

        Consumer<String> announcer = msg -> System.out.println("[Consumer<String>] Announcement: " + msg);
        announcer.accept("All systems nominal. Have a great day, " + owner.getName() + "!");

        Function<String, Integer> nameLength = name -> name.length();
        System.out.println("[Function<String,Integer>] Name length of 'Ceiling Light': "
                + nameLength.apply(ceilingLight.getName()));

        Predicate<String> shortName = name -> name.length() <= 12;
        System.out.println("[Predicate<String>] 'Ceiling Light' is short label: "
                + shortName.test(ceilingLight.getName()));
        System.out.println("[Predicate<String>] 'TV' is short label: " + shortName.test("TV"));

        BiFunction<String, Integer, String> statusLine = (name, brightness) ->
                "Device '" + name + "' brightness=" + brightness + "%";
        System.out.println("[BiFunction<String,Integer,String>] "
                + statusLine.apply("Ceiling Light", 75));

        BiConsumer<String, DeviceStatus> statusLogger = (name, status) ->
                System.out.println("[BiConsumer<String,DeviceStatus>] "
                        + name + " changed status → " + status);
        statusLogger.accept("Front Door Camera", DeviceStatus.MAINTENANCE);

        System.out.println("\n[Done] All homework 2 features demonstrated successfully.");

        System.out.println("\n── STREAMING UTILITIES ──");

        BigDecimal total = Arrays.stream(allDevices)
                .map(Device::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total fleet cost   : $" + total);

        List<Device> byPrice = Arrays.stream(allDevices)
                .sorted((a, b) -> a.getPrice().compareTo(b.getPrice()))
                .collect(Collectors.toList());
        System.out.println("Cheapest device    : " + byPrice.get(0).getName()
                + " ($" + byPrice.get(0).getPrice() + ")");
        System.out.println("Most expensive     : " + byPrice.get(byPrice.size() - 1).getName()
                + " ($" + byPrice.get(byPrice.size() - 1).getPrice() + ")");

        long expensiveCount = Arrays.stream(allDevices)
                .filter(d -> d.getPrice().compareTo(new BigDecimal("150")) > 0)
                .count();
        System.out.println("Devices over $150  : " + expensiveCount);

        String cheapNames = Arrays.stream(allDevices)
                .filter(d -> d.getPrice().compareTo(new BigDecimal("50")) <= 0)
                .map(Device::getName)
                .collect(Collectors.joining(", "));
        System.out.println("Cheap devices (<=50): " + cheapNames);

        boolean hasLuxury = Arrays.stream(allDevices)
                .anyMatch(d -> d.getPrice().compareTo(new BigDecimal("500")) > 0);
        System.out.println("Any luxury device (>$500)? " + hasLuxury);

        Arrays.stream(allDevices)
                .filter(d -> d.getInstalledDate() != null && d.getInstalledDate().getYear() == 2024)
                .findFirst()
                .ifPresent(d -> System.out.println("First 2024 device  : " + d.getName()));

        System.out.println("\n── REFLECTION ──");

        try {
            ReflectionDemo.inspectFields(SmartLight.class);
            ReflectionDemo.inspectFields(Device.class);

            ReflectionDemo.inspectConstructors(SmartLight.class);

            ReflectionDemo.inspectMethods(SmartLight.class);

            SmartLight reflectedLight = ReflectionDemo.createSmartLightViaReflection();

            ReflectionDemo.callMethodViaReflection(reflectedLight, "operate");
            ReflectionDemo.callMethodViaReflection(livingRoomTV, "operate");

            ReflectionDemo.readPrivateField(reflectedLight, "color");
            ReflectionDemo.readPrivateField(livingRoomTV, "brand");
            ReflectionDemo.readPrivateField(ceilingLight, "name");

            System.out.println("\n── @DeviceInfo annotation scanning ──");
            ReflectionDemo.processDeviceInfoAnnotation(ReflectionDemo.SmartHomeDiagnostics.class);
            ReflectionDemo.processDeviceInfoAnnotation(ReflectionDemo.class);

            ReflectionDemo.SmartHomeDiagnostics diagnostics = new ReflectionDemo.SmartHomeDiagnostics();
            ReflectionDemo.runAnnotatedMethods(diagnostics);

        } catch (Exception e) {
            System.out.println("Reflection error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n[Done] All homework 3 features demonstrated successfully.");
    }

    public static void connectDevice(Connectable device) {
        device.connect();
    }
}