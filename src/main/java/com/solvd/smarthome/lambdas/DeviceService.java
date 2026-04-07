package com.solvd.smarthome.lambdas;

import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.enums.AlertLevel;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;


public class DeviceService {

    public void scheduleHealthCheck(Runnable healthCheck) {
        System.out.println("[DeviceService] Scheduling health check...");
        healthCheck.run();
        System.out.println("[DeviceService] Health check complete.");
    }

    public String generateSystemMessage(Supplier<String> messageSupplier) {
        System.out.println("[DeviceService] Requesting system message...");
        String message = messageSupplier.get();
        System.out.println("[DeviceService] Message: " + message);
        return message;
    }

    public void processAllDevices(Device[] devices, Consumer<Device> processor) {
        if (devices == null) return;
        System.out.println("[DeviceService] Processing " + devices.length + " device(s)...");

        Arrays.stream(devices)
                .forEach(processor::accept);
    }

    public String formatDevice(Device device, Function<Device, String> formatter) {
        return formatter.apply(device);
    }

    public Device[] filterDevices(Device[] devices, Predicate<Device> criterion) {
        if (devices == null) return new Device[0];

        Device[] result = Arrays.stream(devices)
                .filter(criterion::test)
                .toArray(Device[]::new);

        System.out.println("[DeviceService] Filtered " + devices.length
                + " → " + result.length + " device(s) passed.");
        return result;
    }

    public String analyseDeviceCost(Device device, BigDecimal ratePerKwh,
                                    BiFunction<Device, BigDecimal, String> costAnalyser) {
        return costAnalyser.apply(device, ratePerKwh);
    }

    public void triggerAlert(Device device, AlertLevel level,
                             BiConsumer<Device, AlertLevel> alertAction) {
        System.out.println("[DeviceService] Triggering alert for: " + device.getName());
        alertAction.accept(device, level);
    }

    public Device[] applyFilter(Device[] devices, DeviceFilter filter) {
        if (devices == null) return new Device[0];

        return Arrays.stream(devices)
                .filter(filter::test)
                .toArray(Device[]::new);
    }

    public void runActionOnAll(Device[] devices, DeviceAction action) {
        if (devices == null) return;

        Arrays.stream(devices)
                .map(action::execute)
                .forEach(r -> System.out.println("  Action result: " + r));
    }


    public void dispatchAlert(String deviceName, AlertLevel level, AlertHandler handler) {
        System.out.println("[DeviceService] Dispatching alert → " + level.name());
        handler.handle(deviceName, level);
    }

    public BigDecimal totalCost(Device[] devices) {
        if (devices == null) return BigDecimal.ZERO;

        return Arrays.stream(devices)
                .map(Device::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Device> sortedByPrice(Device[] devices) {
        if (devices == null) return List.of();

        return Arrays.stream(devices)
                .sorted((a, b) ->
                        a.getPrice().compareTo(b.getPrice()))
                .collect(Collectors.toList());
    }

    public long countExpensive(Device[] devices, BigDecimal threshold) {
        if (devices == null) return 0;

        return Arrays.stream(devices)
                .filter(d -> d.getPrice().compareTo(threshold) > 0)
                .count();
    }

    public String namesOfCheapDevices(Device[] devices, BigDecimal maxPrice) {
        if (devices == null) return "";

        return Arrays.stream(devices)
                .filter(d -> d.getPrice().compareTo(maxPrice) <= 0)
                .map(Device::getName)
                .collect(Collectors.joining(", "));
    }
}