package com.solvd.smarthome.lambdas;

import com.solvd.smarthome.district.house.devices.Device;
import com.solvd.smarthome.enums.AlertLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class DeviceService {

    private static final Logger logger = LogManager.getLogger(DeviceService.class);

    public void scheduleHealthCheck(Runnable healthCheck) {
        logger.info("Scheduling health check...");
        healthCheck.run();
        logger.info("Health check complete.");
    }

    public String generateSystemMessage(Supplier<String> messageSupplier) {
        logger.debug("Requesting system message...");
        String message = messageSupplier.get();
        logger.info("System message generated: {}", message);
        return message;
    }

    public void processAllDevices(Device[] devices, Consumer<Device> processor) {
        if (devices == null) {
            logger.warn("No devices provided for processing.");
            return;
        }

        logger.info("Processing {} device(s)...", devices.length);
        Arrays.stream(devices).forEach(processor);
    }

    public String formatDevice(Device device, Function<Device, String> formatter) {
        return formatter.apply(device);
    }

    public Device[] filterDevices(Device[] devices, Predicate<Device> criterion) {
        if (devices == null) {
            logger.warn("No devices provided for filtering.");
            return new Device[0];
        }

        Device[] result = Arrays.stream(devices)
                .filter(criterion)
                .toArray(Device[]::new);

        logger.info("Filtered {} → {} device(s) passed.", devices.length, result.length);
        return result;
    }

    public String analyseDeviceCost(Device device, BigDecimal ratePerKwh,
                                    BiFunction<Device, BigDecimal, String> costAnalyser) {
        return costAnalyser.apply(device, ratePerKwh);
    }

    public void triggerAlert(Device device, AlertLevel level,
                             BiConsumer<Device, AlertLevel> alertAction) {
        logger.warn("Triggering alert for device: {} with level: {}", device.getName(), level);
        alertAction.accept(device, level);
    }

    public Device[] applyFilter(Device[] devices, DeviceFilter filter) {
        if (devices == null) {
            logger.warn("No devices provided for custom filter.");
            return new Device[0];
        }

        return Arrays.stream(devices)
                .filter(filter::test)
                .toArray(Device[]::new);
    }

    public void runActionOnAll(Device[] devices, DeviceAction action) {
        if (devices == null) {
            logger.warn("No devices provided for actions.");
            return;
        }

        Arrays.stream(devices)
                .map(action::execute)
                .forEach(result -> logger.debug("Action result: {}", result));
    }

    public void dispatchAlert(String deviceName, AlertLevel level, AlertHandler handler) {
        logger.warn("Dispatching alert → {} for device {}", level.name(), deviceName);
        handler.handle(deviceName, level);
    }

    public BigDecimal totalCost(Device[] devices) {
        if (devices == null) {
            logger.warn("No devices provided for cost calculation.");
            return BigDecimal.ZERO;
        }

        BigDecimal total = Arrays.stream(devices)
                .map(Device::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        logger.info("Total cost calculated: {}", total);
        return total;
    }

    public List<Device> sortedByPrice(Device[] devices) {
        if (devices == null) {
            logger.warn("No devices provided for sorting.");
            return List.of();
        }

        return Arrays.stream(devices)
                .sorted((a, b) -> a.getPrice().compareTo(b.getPrice()))
                .collect(Collectors.toList());
    }

    public long countExpensive(Device[] devices, BigDecimal threshold) {
        if (devices == null) {
            logger.warn("No devices provided for counting.");
            return 0;
        }

        long count = Arrays.stream(devices)
                .filter(d -> d.getPrice().compareTo(threshold) > 0)
                .count();

        logger.info("Found {} expensive devices (>{}).", count, threshold);
        return count;
    }

    public String namesOfCheapDevices(Device[] devices, BigDecimal maxPrice) {
        if (devices == null) {
            logger.warn("No devices provided for name extraction.");
            return "";
        }

        String result = Arrays.stream(devices)
                .filter(d -> d.getPrice().compareTo(maxPrice) <= 0)
                .map(Device::getName)
                .collect(Collectors.joining(", "));

        logger.debug("Cheap devices: {}", result);
        return result;
    }
}